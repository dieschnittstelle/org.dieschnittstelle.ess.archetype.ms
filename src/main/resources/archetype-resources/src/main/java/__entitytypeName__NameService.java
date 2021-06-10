package ${groupId};

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;

@Path("/${entitytypeName}Names")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ${entitytypeName}NameService {

    @GET
    public List<String> readUnique${entitytypeName}Names();

}