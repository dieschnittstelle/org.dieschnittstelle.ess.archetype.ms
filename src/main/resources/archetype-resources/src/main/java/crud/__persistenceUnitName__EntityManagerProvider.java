package ${groupId}.crud;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * CDI Bean, die ein Producer-Attribut / Producer-Field fuer eine EntityManager
 * Instanz bereitstellt, welche den Zugriff auf die mit der Persistence Unit
 * "${persistenceUnitName}" assoziierten Datenbestaende ermoeglicht. Durch Verwendung des
 * Qualifiers @${persistenceUnitName}DataAccessor koennen in einem all-in-one Deployment
 * verschiedene EntityManager fuer verschiedene Persistence Units innerhalb eines gemeinsamen
 * CDI Containers verwendet und eindeutig identifiziert werden (siehe MIP:45-46,48).
 *
 * (Quellennachweis, nicht fuer ESS Ruecksprache: Siehe als Vorlgae fuer diese Bereitstellung eines EntityManagers fuer eine spezifische
 * PersistenceUnit: https://www.sitepoint.com/cdi-weld-inject-jpa-hibernate-entity-managers/)
 */
@ApplicationScoped
public class ${persistenceUnitName}EntityManagerProvider {

    /*
     * Bei @${persistenceUnitName}DataAccessor handelt es sich um eine anwendungsspezifische
     * Laufzeit-Annotation, die als CDI Qualifier deklariert wird. (fuer Annotationen siehe BAS:44-53)
     */
    @Qualifier
    /* ? Wie nennt man die Annotationstypen @Retention und @Target? (siehe BAS:51-52) Weshalb? */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public static @interface ${persistenceUnitName}DataAccessor {

    }

    @Produces
    /*
     * Hier wird die oben deklarierte Qualifier-Annotation verwendet, d.h. unter Angabe von
     * @${persistenceUnitName}DataAccessor kann der durch das Producer-Attribut bereit gestellt
     * EntityManger von anderen CDI Komponenten angefordert werden.
     */
    @${persistenceUnitName}DataAccessor
    /*
     * Die @PersistenceContext Annotation ist eine JPA-spezifische Dependency Injection Annotation,
     * auf deren Grundlage eine EntityManager Instanz fuer die angegebene Persistence Unit bereit
     * gestellt wird (siehe JPA:54+1 fuer das "Big Picture" von CDI+JPA sowie die Konfigurationsdatei
     * persistence.xml im resouces/META-INF Verzeichnis fuer die Deklaration der Persistence Unit).
     */
    @PersistenceContext(unitName = "${persistenceUnitName}_PU")
    private EntityManager em;

}