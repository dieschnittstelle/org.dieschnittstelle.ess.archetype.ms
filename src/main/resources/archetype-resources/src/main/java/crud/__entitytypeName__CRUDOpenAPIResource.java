package ${groupId}.crud;

import java.util.List;
import ${groupId}.entities.${entitytypeName}Composite;

import jakarta.persistence.EntityManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

/*
 * CRUD Ressource, die fuer die OpenAPI Beschreibung der server-seitigen WebAPI beruecksichtigt wird
 * (derzeit scheinen Ressourcen bei Trennung von Interface und Implementierung ignoriert zu werden)
 *
 * Wozu dient die OpenAPI Beschreibungssprache, wie ist ein OpenAPI Dokument aufgebaut? (siehe OPI:46-47 und 55-56)
 * Welche Aspekte eines Java Datenmodells koennen mit OpenAPI nicht oder nur partiell ausgedrueckt werden? (siehe OPI:49-50)
 * Welchen Vorteil bietet die Beschreibung eines Service Interfaces mittels OpenAPI verglichen mit der Beschreibung mit JAX-RS? (siehe: OPI:39-40 und 44)
 */
@ApplicationScoped
@Transactional
@Path("/opi/${entitytypeName.toLowerCase()}s")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ${entitytypeName}CRUDOpenAPIResource {

    /*
     * Injektion der Implementierung der CRUD Operationen
     */
    @Inject
    private ${entitytypeName}CRUD crudImpl;

    @GET
    @Operation(operationId = "readAll${entitytypeName}Composites")
    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites() {
        return crudImpl.readAll${entitytypeName}Composites();
    }

    @GET
    @Path("/{id}")
    @Operation(operationId = "read${entitytypeName}Composite")
    public ${entitytypeName}Composite read${entitytypeName}Composite(@PathParam("id") long id) {
        return crudImpl.read${entitytypeName}Composite(id);
    }

    @POST
    @Operation(operationId = "create${entitytypeName}Composite")
    public ${entitytypeName}Composite create${entitytypeName}Composite(${entitytypeName}Composite instance) {
        return crudImpl.create${entitytypeName}Composite(instance);
    }

    @PUT
    @Path("/{id}")
    @Operation(operationId = "update${entitytypeName}Composite")
    public ${entitytypeName}Composite update${entitytypeName}Composite(@PathParam("id") long id,${entitytypeName}Composite instance) {
        return crudImpl.update${entitytypeName}Composite(id,instance);
    }

    @DELETE
    @Path("/{id}")
    @Operation(operationId = "delete${entitytypeName}Composite")
    public boolean delete${entitytypeName}Composite(@PathParam("id") long id) {
        return crudImpl.delete${entitytypeName}Composite(id);
    }

}