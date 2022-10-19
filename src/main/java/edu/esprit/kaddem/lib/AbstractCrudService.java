package edu.esprit.kaddem.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractCrudService<T extends AbstractEntity<?>> {
    @Autowired
    private AbstractRepository<T> repository;

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public T findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public Iterable<T> findAllById(Iterable<Integer> ids) {
        return repository.findAllById(ids);
    }

    public long count() {
        return repository.count();
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<T> entities) {
        repository.deleteAll(entities);
    }

    public void deleteAllById(Iterable<Integer> ids) {
        repository.deleteAllById(ids);
    }

    public List<T> getAll() {
        return repository.findAll();
    }


}
