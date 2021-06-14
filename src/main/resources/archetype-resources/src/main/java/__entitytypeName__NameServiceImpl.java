package ${groupId};

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ${groupId}.entities.${entitytypeName}Composite;
import ${groupId}.crud.${entitytypeName}CRUD;

@ApplicationScoped
@Transactional
public class ${entitytypeName}NameServiceImpl implements ${entitytypeName}NameService {

    @Inject
    private ${entitytypeName}CRUD crudBean;

    public List<String> readUnique${entitytypeName}Names() {
        return crudBean
        .readAll${entitytypeName}Composites()
        .stream()
            .flatMap(e -> Stream.concat(
                Stream.of(e.getName()),
                e.getParts().stream().map(p -> p.getName())))
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    }

}