package edu.esprit.kaddem.lib;

import edu.esprit.kaddem.model.user.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;


public abstract class AbstractCrudController<T extends AbstractEntity<?>, U extends AbstractDto<?>> {
    @Autowired
    private AbstractCrudService<T> service;

    private final ModelMapper mapper = new ModelMapper();

    private U toDto(T entity) {
        Class<U> clazz = (Class<U>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return mapper.map(entity, clazz);
    }

    private T toEntity(U dto) {
        Class<T> clazz = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return mapper.map(dto, clazz);
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

    private Utilisateur getAuthenticatedUser(){
        return new Utilisateur();
    }
}
