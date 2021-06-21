package ${groupId};

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;

/*
 * JAX-RS Application. "Wurzel" der WebAPI, die durch die Webanwendung, in der diese Klasse
 * enthalten ist, bereit gestellt wird. Die URLs für die Zugriff auf die einzelnen Ressourcen / "Services"
 * dieser Application ergeben sich aus der URL der Webanwendung, dem hier angegeben @ApplicationPath,
 * der @Path-URL der Ressourcenklasse bzw. des Interfaces sowie evtl. einer zusätzlichen @Path Operation
 * auf der zuzugreifenden Methode der Ressource (siehe JRS:52).
 */
@ApplicationPath("/api")
public class WebAPIRoot extends Application {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(WebAPIRoot.class);

    public WebAPIRoot() {
        logger.info("<constructor>");
    }

}