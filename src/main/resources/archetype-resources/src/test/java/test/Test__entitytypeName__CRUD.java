package ${groupId}.test;

import ${groupId}.crud.${entitytypeName}CRUD;
import ${groupId}.entities.${entitytypeName};

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        List<${entitytypeName}> initialInstances = apiProxy.readAll${entitytypeName}s();

        assertNotNull(initialInstances,"instances list can be read");

        String name1 = "test instance1";
        String name2 = "test instance2";

        ${entitytypeName} instance1 = new ${entitytypeName}();
        instance1.setName(name1);
        ${entitytypeName} instance2 = new ${entitytypeName}();
        instance2.setName(name2);

        /* CREATE + READ */
        // create two instances
        instance1 = apiProxy.create${entitytypeName}(instance1);
        instance2 = apiProxy.create${entitytypeName}(instance2);

        assertEquals(2,apiProxy.readAll${entitytypeName}s().size()-initialInstances.size(),"instances list is appended on create");

        // read the instances and check whether they are equivalent
        ${entitytypeName} testInstance = apiProxy.read${entitytypeName}(instance1.getId());

        assertNotNull(testInstance,"new instance can be read");
        assertEquals(instance1.getName(),testInstance.getName(),"new instance name is correct");

        /* UPDATE */
        // change the local name
        instance1.setName(instance1.getName() + " " + instance1.getName());
        // update the instance on the server-side
        apiProxy.update${entitytypeName}(instance1.getId(),instance1);

        // read out the instance and compare the names
        testInstance = apiProxy.read${entitytypeName}(instance1.getId());
        assertEquals(instance1.getName(), testInstance.getName(),"instance name is updated correctly");

        /* DELETE */
        assertTrue(apiProxy.delete${entitytypeName}(instance1.getId()),"instance can be deleted");
        assertNull(apiProxy.read${entitytypeName}(instance1.getId()),"deleted instance does not exist anymore");
        assertEquals(initialInstances.size()+1,apiProxy.readAll${entitytypeName}s().size(),"instance list is reduced on delete");
    }

}