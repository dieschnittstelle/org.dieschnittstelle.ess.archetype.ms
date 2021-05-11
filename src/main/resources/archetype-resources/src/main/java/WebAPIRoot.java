package ${groupId};

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.Logger;

@ApplicationPath("/api")
public class WebAPIRoot extends Application {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(WebAPIRoot.class);

    public WebAPIRoot() {
        logger.info("<constructor>");
    }

}