package ${groupId};

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;
import ${groupId}.crud.${entitytypeName}CRUD;

/*
 * Beispiel fuer eine einfache "Geschaeftslogik"-Komponente, die die darunter liegende
 * CRUD Bean fuer den Datenzugriff nutzt, ohne selbst die Details des Datenzugriffs zu kennen
 */
/*
 * Die @ApplicationScoped Annotation gibt an, dass eine Instanz dieser Klasse mit dem globalen Kontext der
 * Anwendung assoziiert ist, die die Klasse enthaelt, d.h. es wird durch den CDI Container nur eine
 * einzige Instanz der Klasse erzeugt, die alle Aufrufe entgegennimmt. Dieser globale Kontext entspricht
 * dem ServletContext von Ja*a EE Web Applikationen (siehe MIP:41ff).
 *
 * ? welche weiteren Kontexte / Geltungsbereiche sind f�r CDI vorgesehen?
 */
@ApplicationScoped
/*
 * Die @Transactional Annotation legt fest, dass alle Datenbankzugriffe, die im Callstack der Methoden
 * dieser Klasse durchgefuehrt werden und in Beans desselben CDI Containers stattfinden, eine gemeinsame
 * Transaktion verwenden, d.h. schreibende Zugriffe auf die verwendete Datenbank werden bis zur Rueckgabe
 * der jeweils aufgerufenen Methode nur "vorgemerkt" und erst dann durch Zugriff auf die Datenbank
 * umgesetzt. Falls Fehler auftreten, werden etwaige bereits getaetigten Zugriffe rueckgaengig gemacht
 * (siehe JPA:43ff).

 * Bei dieser Annotation handelt es sich um eine Interzeptor-Annotation (siehe MIP:47).
 */
@Transactional
public class ${entitytypeName}NameServiceImpl implements ${entitytypeName}NameService {

    /*
     * Die @Inject Annotation bewirkt die Injektion eines Objekts, ueber das der Zugriff auf eine Implementierung
     * des angegebenen Bean-Interfaces ermoeglicht wird. Im vorliegenden Fall handelt es sich hierbei um
     * ein Proxy-Objekt (siehe WSV:41-42 sowie SUM. F�r Dependency Injection allgemein siehe JRS:48).
     * Voraussetzung dafuer, dass die Injektion durchgefuehrt werden kann, ist die eindeutige
     * Ermittelbarkeit einer Implementierung (siehe MIP:44-46).
     *
     * ? wie kann ein privates Instanzattribut, fuer das keine Setter-Methode existiert,
     * von aussen durch ein Framework wie den CDI Container gesetzt werden?
     * (siehe dafuer die Aufzeichnung der Demo zu BAS "Direktzugriff auf Attribute", ca. 9min).
     *
     * ? an welcher anderen Stelle dieser aus Client- und Server-Seite bestehenden Anwendung wird
     * ein Proxy Objekt fuer ${entitytypeName}CRUD verwendet?
     */
    @Inject
    private ${entitytypeName}CRUD crudBean;

    public List<String> readUnique${entitytypeName}Names() {
        return crudBean
        .readAll${entitytypeName}Composites()
        .stream()
            .flatMap(e -> Stream.concat(
                Stream.of(e.getName()),
                e.getParts().stream().map(p -> p.getName())))
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    }

}