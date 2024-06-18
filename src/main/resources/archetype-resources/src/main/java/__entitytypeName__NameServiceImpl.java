package ${groupId};

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
 * dem ServletContext von Ja*a EE Web Applikationen (siehe MIP:55-58).
 *
 * ? welche weiteren Kontexte / Geltungsbereiche sind fuer CDI vorgesehen?
 */
@ApplicationScoped
/*
 * Die @Transactional Annotation legt fest, dass alle Datenbankzugriffe, die im Callstack der Methoden
 * dieser Klasse durchgefuehrt werden und in Beans desselben CDI Containers stattfinden, eine gemeinsame
 * Transaktion verwenden, d.h. schreibende Zugriffe auf die verwendete Datenbank werden bis zur Rueckgabe
 * der jeweils aufgerufenen Methode nur "vorgemerkt" und erst dann durch Zugriff auf die Datenbank
 * umgesetzt. Falls Fehler auftreten, werden etwaige bereits getaetigten Zugriffe rueckgaengig gemacht
 * (siehe JPA:64-67).

 * Die @transactional Annotation bewirkt, dass fuer Methodenaufrufe auf Instanzen dieser Klasse
 * eine Transaktion erstellt wird, mit welcher alle schreibenden Datenbankzugriffe assoziiert sind.
 * Falls die Bean in andere Beans eingebunden wird, welche ihrerseits transaktionale Methoden verwenden,
 * wird die dort ggf. erstellte Transaktion wieder verwendet (siehe JPA:67). Die Erstellung und der
 * Abschluss / das Commit von Transaktionen wird durch den CDI Interzeptor-Mechanismus gehandhabt  (siehe MIP:64),
 * d.h. der Abschluss erfolgt erst und nur dann, wenn die mit der Transaktion initial verbundene Methode
 * abgeschlossen wurde / returned hat.
 */
@Transactional
public class ${entitytypeName}NameServiceImpl implements ${entitytypeName}NameService {

    /*
     * Die @Inject Annotation bewirkt die Injektion eines Objekts, ueber das der Zugriff auf eine Implementierung
     * des angegebenen Bean-Interfaces ermoeglicht wird. Im vorliegenden Fall handelt es sich hierbei um
     * ein Proxy-Objekt (siehe WSV:27ff sowie SUM. Fuer Dependency Injection allgemein siehe JRS:64).
     * Voraussetzung dafuer, dass die Injektion durchgefuehrt werden kann, ist die eindeutige
     * Ermittelbarkeit einer Implementierung (siehe MIP:60-62).
     *
     * ? wie kann ein privates Instanzattribut, fuer das keine Setter-Methode existiert,
     * von aussen durch ein Framework wie den CDI Container gesetzt werden?
     * (siehe dafuer die Aufzeichnung der Demo zu BAS "Direktzugriff auf Attribute", ca. 8:10min ff).
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