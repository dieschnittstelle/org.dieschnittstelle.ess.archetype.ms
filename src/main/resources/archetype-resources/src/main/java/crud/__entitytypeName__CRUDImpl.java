package ${groupId}.crud;

import java.util.List;
import ${groupId}.entities.${entitytypeName}Composite;

import javax.persistence.EntityManager;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.inject.Inject;

@ApplicationScoped
@Transactional
public class ${entitytypeName}CRUDImpl implements ${entitytypeName}CRUD {

    @Inject
    @${persistenceUnitName}EntityManagerProvider.${persistenceUnitName}DataAccessor
    /* siehe auch big picture zu JPA */
    private EntityManager em;

    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites() {

        System.out.println("----- readAll${entitytypeName}Composites()");

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