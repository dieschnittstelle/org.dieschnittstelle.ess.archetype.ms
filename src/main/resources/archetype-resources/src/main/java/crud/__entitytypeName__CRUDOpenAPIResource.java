package ${groupId}.crud;

import java.util.List;
import ${groupId}.entities.${entitytypeName}Composite;

import javax.persistence.EntityManager;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * CRUD Ressource, die fuer die OpenAPI Beschreibung der server-seitigen WebAPI beruecksichtigt wird
 * (derzeit scheinen Ressourcen bei Trennung von Interface und Implementierung ignoriert zu werden)
 *
 * Wozu dient die OpenAPI Beschreibungssprache, wie ist ein OpenAPI Dokument aufgebaut? (siehe OPI:24 und 31-32)
 * Welche Aspekte eines Java Datenmodells koennen mit OpenAPI nicht oder nur partiell ausgedrueckt werden? (siehe OPI:34-35)
 * Welchen Vorteil bietet die Beschreibung eines Service Interfaces mittels OpenAPI verglichen mit der Beschreibung mit JAX-RS? (siehe: OPI:18-21 und 26)
 */
@ApplicationScoped
@Transactional
@Path("/opi/${entitytypeName}s")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ${entitytypeName}CRUDOpenAPIResource {

    /*
     * Injektion der Implementierung der CRUD Operationen
     */
    @Inject
    private ${entitytypeName}CRUD crudImpl;

    @GET
    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites() {
        return crudImpl.readAll${entitytypeName}Composites();
    }

    @GET
    @Path("/{id}")
    public ${entitytypeName}Composite read${entitytypeName}Composite(@PathParam("id") long id) {
        return crudImpl.read${entitytypeName}Composite(id);
    }

    @POST
    public ${entitytypeName}Composite create${entitytypeName}Composite(${entitytypeName}Composite instance) {
        return crudImpl.create${entitytypeName}Composite(instance);
    }

    @PUT
    @Path("/{id}")
    public ${entitytypeName}Composite update${entitytypeName}Composite(@PathParam("id") long id,${entitytypeName}Composite instance) {
        return crudImpl.update${entitytypeName}Composite(id,instance);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete${entitytypeName}Composite(@PathParam("id") long id) {
        return crudImpl.delete${entitytypeName}Composite(id);
    }

}