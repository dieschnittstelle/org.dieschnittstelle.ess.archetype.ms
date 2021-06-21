package ${groupId}.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/*
 * Beispiel fuer eine Klasse, deren Instanzen Kompositionen ggf. mehrerer
 * Instanzen von ${entitytypeName}Part enthalten können. Instanzen dieser Klasse
 * werden in dieser Anwendung in zwei Verwendungsformen auf:
 * - als POJOs (bzw. Data Transfer Objekte) auf Seiten der Client-Anwendung Test${entitytypeName}CRUD und
 * an der Client-Server Schnittstelle der durch die server-seitige Anwendung bereit gestellten Web API
 * - als JPA Entities im Kontext der Verwendung des JPA Entity Managers in ${entitytypeName}CRUDImpl
 * (siehe SUM:12-13)
 *
 * ? ist die @Entity Annotation auf Ebene der Client-Anwendung relevant?
 * ? ist die Client-Anwendung ausfuehrbar, wenn die @Entity Annotationsklasse zur Laufzeit des Clients nicht bekannt ist? (siehe BAS:48)
 *
 */
@Entity
public class ${entitytypeName}Composite {

    /*
     * Durch @Id wird fuer die JPA Implementierung zum Ausdruck gebracht, dass
     * annotierte Attribut der Primary Key Spalte der Tabelle entspricht, in der
     * Instanzen von ${entitytypeName}Composite persistiert werden (siehe JPA:25).
     */
    @Id
    /*
     * @GeneratedValue bewirkt, dass die Werte fuer das id Attribut durch die
     * verwendete Datenbank zugewiesen werden.
     */
    @GeneratedValue
    private long id;

    /*
     * @oneToMany drueckt die Kardinalitaet der parts Assoziation aus
     *
     * ? inwiefern ist diese Information ohne Angabe der Annotation nur partiell vorhanden?
     */
    /*
     * Mittels des cascade Parameters wird angegeben, wie Instanzen von ${entitytypeName}Part
     * im Hinblick auf die Kaskadierung der auf ${entitytypeName}Composite durchgefuerten CRUD
     * Operationen behandelt werden sollen (siehe JPA:39ff).
     *
     * Könnte man CascadeType.ALL auch fuer eine @ManyToMany Assoziation sinnvoll angeben?
     */
    /*
     * Der fetch-Parameter gibt an, dass beim Auslesen von ${entitytypeName}Composite auch die ggf.
     * mit der ausgelesenen Instanz assoziierten Instanzen von ${entitytypeName}Part ausgelesen werden
     * sollen.
     */
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<${entitytypeName}Part> parts = new HashSet();

    public ${entitytypeName}Composite() {

    }

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParts(Set<${entitytypeName}Part> parts) {
        this.parts = parts;
    }

    public Set<${entitytypeName}Part> getParts() {
        return this.parts;
    }


}

