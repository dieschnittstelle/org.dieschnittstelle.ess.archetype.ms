package ${groupId}.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;


@Entity
public class ${entitytypeName}Composite {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade=CascadeType.ALL)
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

