package ${groupId};

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * JAX-RS Application. "Wurzel" der WebAPI, die durch die Webanwendung, in der diese Klasse
 * enthalten ist, bereit gestellt wird. Die URLs fuer die Zugriffe auf die einzelnen Ressourcen / "Services"
 * dieser Application ergeben sich aus der URL der Webanwendung, dem hier angegeben @ApplicationPath,
 * der @Path-URL der Ressourcenklasse bzw. des Interfaces sowie evtl. einer zusaetzlichen @Path Annotation
 * auf der zuzugreifenden Methode der Ressource (siehe JRS:40+2:"WebAPI mit JAX-RS und Resteasy").
 */
@ApplicationPath("/api")
public class WebAPIRoot extends Application {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(WebAPIRoot.class);

    public WebAPIRoot() {
        logger.info("<constructor>");
    }

    /*
     * Um eine client-seitig verwendbare OpenAPI Beschreibung zu erhalten, fuer die funktionierender
     * Javacode generiert werden kann, muessen derzeit die Klassen, die die WebAPI implementieren,
     * explizit aufgelistet werden.
     */
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(${entitytypeName}NameServiceImpl.class, ${groupId}.crud.${entitytypeName}CRUDImpl.class, ${groupId}.crud.${entitytypeName}CRUDOpenAPIResource.class));
    }

}