package ${groupId}.crud;

import java.util.List;
import ${groupId}.entities.${entitytypeName};

import javax.persistence.EntityManager;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.inject.Inject;

@RequestScoped
@Transactional
public class ${entitytypeName}CRUDImpl implements ${entitytypeName}CRUD {

    @Inject
    @${persistenceUnitName}EntityManagerProvider.${persistenceUnitName}DataAccessor
    private EntityManager em;

    public List<${entitytypeName}> readAll${entitytypeName}s() {
        return (List)em
                .createQuery("SELECT e FROM ${entitytypeName} e")
                .getResultList();
    }

    public ${entitytypeName} read${entitytypeName}(long id) {
        return em.find(${entitytypeName}.class,id);
    }

    public ${entitytypeName} create${entitytypeName}(${entitytypeName} instance) {
        em.persist(instance);
        return instance;
    }

    public ${entitytypeName} update${entitytypeName}(long id,${entitytypeName} instance) {
        instance = em.merge(instance);
        return instance;
    }

    public boolean delete${entitytypeName}(long id) {
        em.remove(this.read${entitytypeName}(id));
        return true;
    }

}