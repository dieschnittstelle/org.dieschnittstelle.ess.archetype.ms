package ${groupId};

import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;

/*
 * Interface fuer eine "Geschaeftslogik"-Komponente, die die Namen der existierenden ${entitytypeName}
 * Instanzen auf Basis elementarer CRUD Operationen ermittelt
 */

/*
 * Die @Path Annotation gibt die Wurzel-URL dieses Services bzw. dieser JAX-RS Ressource unterhalb der
 * URL der JAX-RS Application an (siehe dafuer die WebAPIRoot Klasse).
 */
@Path("/${entitytypeName.toLowerCase()}Names")
/*
 * Die @Produces/@Consumes Annotationen geben an, dass beim Argumente und Rueckgabewerte (falls vorhanden)
 * im JSON Format uebermittelt werden
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ${entitytypeName}NameService {

    /*
     * Die @GET Annotation gibt an, dass fuer den Zugriff auf die annotierte Operation
     * ein HTTP GET Request verwendet werden soll
     */
    @GET
    public List<String> readUnique${entitytypeName}Names();

}