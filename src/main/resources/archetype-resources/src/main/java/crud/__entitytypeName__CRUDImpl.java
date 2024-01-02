package ${groupId}.crud;

import java.util.List;
import ${groupId}.entities.${entitytypeName}Composite;

import jakarta.persistence.EntityManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

/*
 * Typische Implementierung eines CRUD Interfaces als zustandslose transaktionale
 * CDI Bean
 *
 * ? welche Voraussetzung muss gegeben sein, damit diese Klasse als @ApplicationScoped deklariert werden kann? (siehe MIP:56)
 */
@ApplicationScoped
@Transactional
public class ${entitytypeName}CRUDImpl implements ${entitytypeName}CRUD {

    /*
     * Injektion des durch das Producer-Attribut der Klasse ${persistenceUnitName}EntityManagerProvider
     * bereit gestellten Entity Managers
     */
    @Inject
    @${persistenceUnitName}EntityManagerProvider.${persistenceUnitName}DataAccessor
    private EntityManager em;

    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites() {

        System.out.println("----- readAll${entitytypeName}Composites()");

        /*
         * einfaches Beispiel fuer die Verwendung der JPA Query Language
         * ? ist die Kenntnis der genauen Datenbankstruktur fuer die
         * Formulierung von JPQL Abfragen erforderlich? (siehe JPA:43-44)
         */
        return (List)em
                .createQuery("SELECT e FROM ${entitytypeName}Composite e")
                .getResultList();
    }

    public ${entitytypeName}Composite read${entitytypeName}Composite(long id) {
        System.out.println("----- read${entitytypeName}Composite(): " + id);

        return em.find(${entitytypeName}Composite.class,id);
    }

    public ${entitytypeName}Composite create${entitytypeName}Composite(${entitytypeName}Composite instance) {
        System.out.println("----- create${entitytypeName}Composite(): " + instance);
        /*
         * Welche Voraussetzung muss fuer eine erfolgreiche Ausfuehrung der persist() Methode
         * fuer eine gegebene Instanz von ${entitytypeName}Composite erfuellt sein? (siehe JPA:61)
         */
        em.persist(instance);
        return instance;
    }

    public ${entitytypeName}Composite update${entitytypeName}Composite(long id,${entitytypeName}Composite instance) {
        System.out.println("----- update${entitytypeName}Composite(): " + id + ": " + instance);
        instance = em.merge(instance);
        return instance;
    }

    public boolean delete${entitytypeName}Composite(long id) {
        System.out.println("----- delete${entitytypeName}Composite(): " + id);

        em.remove(this.read${entitytypeName}Composite(id));
        return true;
    }

}