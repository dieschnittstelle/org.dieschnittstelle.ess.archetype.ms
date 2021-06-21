package ${groupId}.crud;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;

/*
 * Beispiel fuer ein typisches CRUD Interface in Java, das nach dem REST Prinzip des
 * "Uniformen Interfaces" für den Zugriff ueber eine Web API mit JAX-RS Annotationen
 * versehen wird (siehe JRS:27).
 * Grundlage fuer die Zuordnung von HTTP Methoden zu CRUD Operationen ist die Semantik
 * der HTTP Methoden entsprechend der HTTP Spezifikation (siehe JRS:25-26).
 *
 * ? welchem Pattern entspricht die Deklaration eines Interfaces fuer Datenzugriffe,
 * gegen das die Geschaeftslogikschicht einer Anwendung implementiert werden kann? (siehe PAT)
 */
@Path("/${entitytypeName}s")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ${entitytypeName}CRUD {

    @GET
    public List<${entitytypeName}Composite> readAll${entitytypeName}Composites();

    /*
     * Die @Path Annotation kann auf Ebene einer Methode angegeben werden, um eine Unter-Ressource
     * der Wurzel-Resource anzugeben, die durch die @Path-Annotation auf Interface-Ebene identifiziert wird.
     * Im vorliegenden Beispiel handelt es sich bei der Wurzel-Ressource um "die Menge aller
     * ${entitytypeName}Composite-Instanzen", und bei der hier zugegriffenen Unter-Ressource um ein
     * einzelnes Element dieser Menge, das durch einen Identifikator ("id") identifiziert wird.
     *
     * Das dynamische URL-Segment {id}, das in @Path verwendet wird, wird durch @PathParam an
     * das id-Argument der Methode gebunden, d.h. server-seitig wird durch die JAx-RS Implementierung
     * der jeweilige String-Wert dieses Segments in einen long-Wert umgewandelt und dann die Methode aufgerufen,
     *
     * ? welches typische Architekturmerkmal von Frameworks kommt bei diesem Aufruf zur Anwendung? (siehe BAS:16)
     */
    @GET
    @Path("/{id}")
    public ${entitytypeName}Composite read${entitytypeName}Composite(@PathParam("id") long id);

    /*
     * ? inwiefern ist die Verwendung von @Post fuer create() und @PUT fuer update() durch die Semantik der HTTP Methoden vorgegeben?
     */
    @POST
    public ${entitytypeName}Composite create${entitytypeName}Composite(${entitytypeName}Composite instance);

    @PUT
    @Path("/{id}")
    public ${entitytypeName}Composite update${entitytypeName}Composite(@PathParam("id") long id,${entitytypeName}Composite instance);

    /*
     *  ? inwiefern ist eine delete() Operation im Sinne der HTTP Spezifikation idempotent?
     */
    @DELETE
    @Path("/{id}")
    public boolean delete${entitytypeName}Composite(@PathParam("id") long id);

}