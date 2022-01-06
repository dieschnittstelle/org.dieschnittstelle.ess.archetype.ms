package ${groupId}.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/*
 * Beispiel fuer eine Klasse, deren Instanzen als Composites ggf. mehrere
 * Instanzen von ${entitytypeName}Part enthalten koennen. Instanzen dieser Klasse
 * treten in dieser Anwendung in zwei Verwendungsformen auf:
 * - als POJOs (bzw. Data Transfer Objekte) auf Seiten der Client-Anwendung Test${entitytypeName}CRUD und
 * an der Client-Server Schnittstelle der durch die server-seitige Anwendung bereit gestellten Web API
 * - als JPA Entities im Kontext der Verwendung des JPA Entity Managers in ${entitytypeName}CRUDImpl
 * (siehe SUM:19-20)
 *
 * ? ist die @Entity Annotation auf Ebene der Client-Anwendung relevant?
 * ? ist die Client-Anwendung ausfuehrbar, wenn die @Entity Annotationsklasse zur Laufzeit des Clients nicht bekannt ist? (siehe BAS:54)
 *
 */
@Entity
/*
 * Angabe des Namens des Objektschemas, unter dem der dieser Klasse entsprechende Datentyp in der OpenAPI Beschreibung der
 * Web API identifiziert wird (derzeit (September 2021) besteht jedoch die Problematik, dass JAX-RS Ressourcen, die eine
 * Trennung von Interface und Implementierung vorsehen, bei der OpenAPI Generierung durch die verwendete TomEE Version ignoriert werden)
 */
@Schema(name = "${entitytypeName}Composite")
public class ${entitytypeName}Composite {

    /*
     * Durch @Id wird fuer die JPA Implementierung zum Ausdruck gebracht, dass das
     * annotierte Attribut der Primary Key Spalte der Tabelle entspricht, in der
     * Instanzen von ${entitytypeName}Composite persistiert werden (siehe JPA:41).
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
     * Operationen behandelt werden sollen (siehe JPA:55ff).
     *
     * Koennte man CascadeType.ALL auch fuer eine @ManyToMany Assoziation sinnvoll angeben?
     */
    /*
     * Der fetch-Parameter gibt an, dass beim Auslesen von ${entitytypeName}Composite auch die ggf.
     * mit der ausgelesenen Instanz assoziierten Instanzen von ${entitytypeName}Part ausgelesen werden
     * sollen.
     */
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    /*
     * Explizite Angabe, dass bei Generierung des Objektschemas das durch die assoziierte
     * Klasse deklarierte Schema verwendet werden soll
     */
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

