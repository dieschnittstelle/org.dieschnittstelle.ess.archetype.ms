package ${groupId}.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/*
 * Klasse, deren Instanzen als Bestandteile von ${entitytypeName}Composite verwendet werden
 *
 * ? weshalb sind in ${entitytypeName}CRUDImpl  fuer ggf. in einer ${entitytypeName}Composite Instanz enthaltene
 * Instanzen dieser Klasse keine persist() Aufrufe auf dem verwendeten Entity Manager erforderlich?
 */
@Entity
@Schema(name = "${entitytypeName}Part")
public class ${entitytypeName}Part {

    @Id
    @GeneratedValue
    private long id;

    public ${entitytypeName}Part() {

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

}

