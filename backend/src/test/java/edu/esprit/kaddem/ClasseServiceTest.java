package edu.esprit.kaddem;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.services.ClasseService;
import edu.esprit.kaddem.lib.AbstractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClasseServiceTest {

    @Mock
    private AbstractRepository<Classe> repository;

    @InjectMocks
    private ClasseService classeService;

    @Test
    void createClasse() {
        Classe classe = new Classe("TestClass");
        when(repository.save(classe)).thenReturn(classe);

        Classe result = classeService.create(classe);

        verify(repository, times(1)).save(classe);
        assertSame(result, classe);
    }

    @Test
    void updateClasse() {
        Classe existingClasse = new Classe("TestClass");
        existingClasse.setId(1);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(existingClasse)).thenReturn(existingClasse);

        Classe updatedClasse = classeService.update(1, existingClasse);

        verify(repository, times(1)).save(existingClasse);
        assertSame(existingClasse, updatedClasse);
    }

    @Test
    void updateClasse_EntityNotFoundException() {
        Classe classe = new Classe("TestClass");
        when(repository.existsById(1)).thenReturn(false);

        // Make sure that an EntityNotFoundException is thrown when trying to update a non-existing entity
        assertThrows(EntityNotFoundException.class, () -> classeService.update(1, classe));

        verify(repository, never()).save(classe);
    }

    @Test
    void deleteClasse() {
        Classe classe = new Classe("TestClass");

        classeService.delete(classe);

        verify(repository, times(1)).delete(classe);
    }

    @Test
    void findById() {
        Classe existingClasse = new Classe("TestClass");
        existingClasse.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(existingClasse));

        Classe result = classeService.findById(1);

        verify(repository, times(1)).findById(1);
        assertSame(existingClasse, result);
    }

    @Test
    void findById_EntityNotFoundException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        // Make sure that an EntityNotFoundException is thrown when trying to find a non-existing entity
        assertThrows(EntityNotFoundException.class, () -> classeService.findById(1));

        verify(repository, times(1)).findById(1);
    }

    // Add more tests for other methods as needed

}
