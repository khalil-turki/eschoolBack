package edu.esprit.kaddem.lib;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    @GetMapping("{id}")
    public U findById(@PathParam("id") Integer id) {
        return toDto(service.findById(id));
    }

    @PostMapping()
    public U create(@RequestBody U dto) {
        var entity = toEntity(dto);
        var created = service.create(entity);
        return toDto(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public U update(@RequestBody U dto) {
        return toDto(service.update(toEntity(dto)));
    }
}
