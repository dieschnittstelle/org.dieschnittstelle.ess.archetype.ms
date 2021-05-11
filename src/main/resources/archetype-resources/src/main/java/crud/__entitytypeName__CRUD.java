package ${groupId}.crud;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName};

@Path("/${entitytypeName}s")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ${entitytypeName}CRUD {

    @GET
    public List<${entitytypeName}> readAll${entitytypeName}s();

    @GET
    @Path("/{id}")
    public ${entitytypeName} read${entitytypeName}(@PathParam("id") long id);

    @POST
    public ${entitytypeName} create${entitytypeName}(${entitytypeName} instance);

    @PUT
    @Path("/{id}")
    public ${entitytypeName} update${entitytypeName}(@PathParam("id") long id,${entitytypeName} instance);

    @DELETE
    @Path("/{id}")
    public boolean delete${entitytypeName}(@PathParam("id") long id);

}