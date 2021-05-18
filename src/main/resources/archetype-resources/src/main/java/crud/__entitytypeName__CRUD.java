package ${groupId}.crud;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;

@Path("/${entitytypeName}s")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ${entitytypeName}CRUD {

    @GET
    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites();

    @GET
    @Path("/{id}")
    public ${entitytypeName}Composite read${entitytypeName}Composite(@PathParam("id") long id);

    @POST
    public ${entitytypeName}Composite create${entitytypeName}Composite(${entitytypeName}Composite instance);

    @PUT
    @Path("/{id}")
    public ${entitytypeName}Composite update${entitytypeName}Composite(@PathParam("id") long id,${entitytypeName}Composite instance);

    @DELETE
    @Path("/{id}")
    public boolean delete${entitytypeName}Composite(@PathParam("id") long id);

}