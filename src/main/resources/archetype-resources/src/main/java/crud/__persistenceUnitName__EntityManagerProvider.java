package ${groupId}.crud;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApplicationScoped
public class ${persistenceUnitName}EntityManagerProvider {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public static @interface ${persistenceUnitName}DataAccessor {

    }

    @Produces
    @${persistenceUnitName}DataAccessor
    @PersistenceContext(unitName = "${persistenceUnitName}_PU")
    private EntityManager em;

}