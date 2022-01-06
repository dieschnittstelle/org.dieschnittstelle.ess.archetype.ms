package ${groupId}.test;

import ${groupId}.${entitytypeName}NameService;
import ${groupId}.crud.${entitytypeName}CRUD;
import ${groupId}.entities.${entitytypeName}Composite;
import ${groupId}.entities.${entitytypeName}Part;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * see https://github.com/AdamBien/JakartaEE-essentials-archetype/find/master as a basis for this implementation
 */
public class Test${entitytypeName}CRUD {

    private static ${entitytypeName}CRUD crudProxy;
    private static ${entitytypeName}NameService businessProxy;

    @BeforeAll
    public static void createAPIProxy() {
        /*
         * Hier wird mit den Mitteln der Rest Client Implementierung aus Eclipse Microprofile
         * fuer die beiden angegebenen Interfaces ${entitytypeName}CRUD und
         * ${entitytypeName}NameService jeweils ein Proxy Objekt generiert (siehe WSV:19-2ß).
         *
         * ? wie und wo kann dieser Mechanismus im Rahmen von Dependency Injection
         * im Hintergrund genutzt werden? (siehe MSD:39)
         */
        URI uri = URI.create("http://localhost:${tomeeHttpPort}/api");
        RestClientBuilder builder = RestClientBuilder
                .newBuilder()
                .baseUri(uri)
                .register(JacksonJaxbJsonProvider.class);

        crudProxy = builder.build(${entitytypeName}CRUD.class);
        businessProxy = builder.build(${entitytypeName}NameService.class);
    }

    @Test
    public void run() {
        // read all instances
        List<${entitytypeName}Composite> initialInstances = crudProxy.readAll${entitytypeName}Composites();

        assertNotNull(initialInstances,"instances list can be read");

        // we determine all names of instances in order to have a baseline for the tests
        List<String> allInitialNames = businessProxy.readUnique${entitytypeName}Names();

        String name1 = "composite1";
        String name2 = "composite2";
        String name3 = "composite3";
        String name11 = "part1";
        String name21 = "part2";
        // this is an extra name for a third composite
        String name33 = "part3";

        ${entitytypeName}Composite instance1 = new ${entitytypeName}Composite();
        instance1.setName(name1);
        ${entitytypeName}Composite instance2 = new ${entitytypeName}Composite();
        instance2.setName(name2);
        // a third instance that will get the same name parts as instance2
        ${entitytypeName}Composite instance3 = new ${entitytypeName}Composite();
        instance3.setName(name3);

        ${entitytypeName}Part part1 = new ${entitytypeName}Part();
        part1.setName(name11);
        ${entitytypeName}Part part2 = new ${entitytypeName}Part();
        part2.setName(name21);
        ${entitytypeName}Part part3 = new ${entitytypeName}Part();
        part3.setName(name33);

        instance1.getParts().add(part1);
        instance2.getParts().add(part2);
        instance3.getParts().add(part1);
        instance3.getParts().add(part2);
        instance3.getParts().add(part3);

        /* CREATE + READ */
        // create two instances
        instance1 = crudProxy.create${entitytypeName}Composite(instance1);
        instance2 = crudProxy.create${entitytypeName}Composite(instance2);
        instance3 = crudProxy.create${entitytypeName}Composite(instance3);

        assertEquals(3, crudProxy.readAll${entitytypeName}Composites().size()-initialInstances.size(),"instances list is appended on create");

        // read the instances and check whether they are equivalent
        ${entitytypeName}Composite testInstance = crudProxy.read${entitytypeName}Composite(instance1.getId());

        assertNotNull(testInstance,"new instance can be read");
        assertEquals(instance1.getName(),testInstance.getName(),"new instance name is correct");

        /* UPDATE */
        // change the local name
        instance1.setName(instance1.getName() + " " + instance1.getName());
        // update the instance on the server-side
        crudProxy.update${entitytypeName}Composite(instance1.getId(),instance1);

        // read out the instance and compare the names
        testInstance = crudProxy.read${entitytypeName}Composite(instance1.getId());
        assertEquals(instance1.getName(), testInstance.getName(),"instance name is updated correctly");

        /* BUSINESS METHOD: READ ALL NAMES */
        Set<String> alllocalnames = new HashSet<>();
        alllocalnames.add(instance1.getName());
        alllocalnames.add(instance2.getName());
        alllocalnames.add(instance3.getName());
        alllocalnames.add(part1.getName());
        alllocalnames.add(part2.getName());
        alllocalnames.add(part3.getName());

        // remove the local names from the initial names - note that this requires for the name sets to be distinct!
        allInitialNames.removeAll(alllocalnames);

        List<String> allReceivedInstanceNames = businessProxy.readUnique${entitytypeName}Names();
        // and remove the initial names from the received names
        allReceivedInstanceNames.removeAll(allInitialNames);

        // compare the length of the set
        assertEquals(allReceivedInstanceNames.size(),alllocalnames.size(),"local and remote name sets have same size");
        // then remove the local names from the remote ones to see whether they match
        allReceivedInstanceNames.removeAll(alllocalnames);
        assertEquals(0,allReceivedInstanceNames.size(),"local and remote name sets contain the same elements");

        /* DELETE */
        assertTrue(crudProxy.delete${entitytypeName}Composite(instance1.getId()),"instance can be deleted");
        assertNull(crudProxy.read${entitytypeName}Composite(instance1.getId()),"deleted instance does not exist anymore");
        assertEquals(initialInstances.size()+2, crudProxy.readAll${entitytypeName}Composites().size(),"instance list is reduced on delete");
    }

}