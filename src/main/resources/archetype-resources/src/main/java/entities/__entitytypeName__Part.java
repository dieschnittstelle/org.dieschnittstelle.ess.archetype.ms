package ${groupId}.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Klasse, deren Instanzen als Bestandteile von ${entitytypeName}Composite verwendet werden
 *
 * ? weshalb sind in ${entitytypeName}CRUDImpl  fuer ggf. in einer ${entitytypeName}Composite Instanz enthaltene
 * Instanzen dieser Klasse keine persist() Aufrufe auf dem verwendeten Entity Manager erforderlich?
 */
@Entity
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

