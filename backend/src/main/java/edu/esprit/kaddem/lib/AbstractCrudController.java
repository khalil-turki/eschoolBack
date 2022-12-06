package edu.esprit.kaddem.lib;

import edu.esprit.kaddem.dto.search.PagedResponse;
import edu.esprit.kaddem.dto.search.SearchRequest;
import edu.esprit.kaddem.dto.search.util.SearchRequestUtil;
import edu.esprit.kaddem.model.user.Utilisateur;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractCrudController<T extends AbstractEntity<?>, U extends AbstractDto<?>> {
    @Autowired
    private AbstractCrudService<T> service;

    private final ModelMapper mapper = new ModelMapper();
    private final Class<U> dtoClass;
    private final Class<T> entityClass;

    public AbstractCrudController() {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dtoClass = (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        mapper.createTypeMap(entityClass, dtoClass);
        mapper.createTypeMap(dtoClass, entityClass);
    }

    protected U toDto(T entity) {
        return mapper.map(entity, this.dtoClass);
    }

    private T toEntity(U dto) {
        return mapper.map(dto, entityClass);
    }


    @GetMapping()
    public Iterable<U> findAll() {
        return service.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public U findById(@PathVariable("id") Integer id) {
        return toDto(service.findById(id));
    }

    @PostMapping()
    public U create(@RequestBody U dto) {
        var entity = toEntity(dto);
        var created = service.create(entity);
        return toDto(created);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public U update(@PathVariable("id") Integer id, @RequestBody U dto) {
        var newEntity = toEntity(dto);
        var updated = service.update(id, newEntity);
        return toDto(updated);
    }

    @PatchMapping(path="/{id}", consumes = "application/merge-patch+json")
    public U patch(@PathVariable("id") Integer id, @RequestBody JsonMergePatch patch) {
        return toDto(service.patch(id, patch));
    }
    @GetMapping("/list")
    public List<U> list(SearchRequest searchRequest) {
        var list = service.list(searchRequest);
        return list.getContent().stream().map(this::toDto).collect(Collectors.toList());
    }

    private Utilisateur getAuthenticatedUser(){
        return (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
