package ${groupId}.test;

import ${groupId}.crud.${entitytypeName}CRUD;
import ${groupId}.entities.${entitytypeName}Composite;
import ${groupId}.entities.${entitytypeName}Part;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * see https://github.com/AdamBien/JakartaEE-essentials-archetype/find/master as a basis for this implementation
 */
public class Test${entitytypeName}CRUD {

    private static ${entitytypeName}CRUD apiProxy;

    @BeforeAll
    public static void createAPIProxy() {
        URI uri = URI.create("http://localhost:${tomeeHttpPort}/api");
        apiProxy = RestClientBuilder
                .newBuilder()
                .baseUri(uri)
                .register(JacksonJaxbJsonProvider.class)
                .build(${entitytypeName}CRUD .class);
    }

    @Test
    public void run() {
        // read all instances
        List<${entitytypeName}Composite> initialInstances = apiProxy.readAll${entitytypeName}Composites();

        assertNotNull(initialInstances,"instances list can be read");

        String name1 = "composite1";
        String name2 = "composite2";
        String name11 = "part1";
        String name21 = "part2";

        ${entitytypeName}Composite instance1 = new ${entitytypeName}Composite();
        instance1.setName(name1);
        ${entitytypeName}Composite instance2 = new ${entitytypeName}Composite();
        instance2.setName(name2);

        ${entitytypeName}Part part1 = new ${entitytypeName}Part();
        part1.setName(name11);
        ${entitytypeName}Part part2 = new ${entitytypeName}Part();
        part2.setName(name21);

        instance1.getParts().add(part1);
        instance2.getParts().add(part2);


        /* CREATE + READ */
        // create two instances
        instance1 = apiProxy.create${entitytypeName}Composite(instance1);
        instance2 = apiProxy.create${entitytypeName}Composite(instance2);

        assertEquals(2,apiProxy.readAll${entitytypeName}Composites().size()-initialInstances.size(),"instances list is appended on create");

        // read the instances and check whether they are equivalent
        ${entitytypeName}Composite testInstance = apiProxy.read${entitytypeName}Composite(instance1.getId());

        assertNotNull(testInstance,"new instance can be read");
        assertEquals(instance1.getName(),testInstance.getName(),"new instance name is correct");

        /* UPDATE */
        // change the local name
        instance1.setName(instance1.getName() + " " + instance1.getName());
        // update the instance on the server-side
        apiProxy.update${entitytypeName}Composite(instance1.getId(),instance1);

        // read out the instance and compare the names
        testInstance = apiProxy.read${entitytypeName}Composite(instance1.getId());
        assertEquals(instance1.getName(), testInstance.getName(),"instance name is updated correctly");

        /* DELETE */
        assertTrue(apiProxy.delete${entitytypeName}Composite(instance1.getId()),"instance can be deleted");
        assertNull(apiProxy.read${entitytypeName}Composite(instance1.getId()),"deleted instance does not exist anymore");
        assertEquals(initialInstances.size()+1,apiProxy.readAll${entitytypeName}Composites().size(),"instance list is reduced on delete");
    }

}