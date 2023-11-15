package edu.esprit.kaddem;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Parent;

import edu.esprit.kaddem.repository.EtudiantRepository;

import edu.esprit.kaddem.services.EtudiantService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantService etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Create a sample list of Etudiant objects
        List<Etudiant> etudiantList = Collections.singletonList(new Etudiant());

        // Mock the repository to return the sample list when findAll is called
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        List<Etudiant> result = etudiantService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    public void testAddEtudiant() {
        // Given
        List<Parent> parents = new ArrayList<>();
        Parent parent1 = new Parent(/* initialize with appropriate values */);
        Parent parent2 = new Parent(/* initialize with appropriate values */);
        parents.add(parent1);
        parents.add(parent2);

        Etudiant etudiant = new Etudiant();
        etudiant.setParents(parents);
        etudiant.setClasse(new Classe("test", "test", null, null));
        etudiant.setAdresse(new Adresse("dd", "test", "test", "test", "test"));
        etudiant.setNom("test");
        etudiant.setPrenom("test");
        etudiant.setEmail("test");
        etudiant.setDateDeNaissance(new Date());
        etudiant.setNumTel("test");

        // Mock repository save method
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant addedEtudiant = etudiantService.create(etudiant);

        // Then
        // Verify that the repository's save method was called with the expected etudiant
        verify(etudiantRepository).save(etudiant);

        // Assert the result
        assertNotNull(addedEtudiant);
        // You may want to assert more conditions based on your specific requirements
    }


    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(1); // Set the ID for testing

        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.findById(1);

        assertNotNull(retrievedEtudiant);
        assertEquals(1, retrievedEtudiant.getId().intValue());
    }


    @Test
    void testRemoveEtudiant() {
        // Setup the repository mock
        lenient().when(etudiantRepository.findById(1)).thenReturn(Optional.of(new Etudiant()));

        // Call the service method that you are testing
        etudiantService.deleteById(1);

        // Verify that deleteById was called with the correct argument
        verify(etudiantRepository).deleteById(1);

        // Since your service method calls deleteById, there is no need to verify delete with an Etudiant object.
    }


    @Test
    public void testUpdateEtudiant() {

        // Given
        List<Parent> parents = new ArrayList<>();
        Parent parent1 = new Parent(/* initialize with appropriate values */);
        Parent parent2 = new Parent(/* initialize with appropriate values */);
        parents.add(parent1);
        parents.add(parent2);

        Etudiant etudiant = new Etudiant();
        etudiant.setParents(parents);
        etudiant.setClasse(new Classe("test", "test", null, null));
        etudiant.setAdresse(new Adresse("dd", "test", "test", "test", "test"));
        etudiant.setNom("test");
        etudiant.setPrenom("test");
        etudiant.setEmail("test");
        etudiant.setDateDeNaissance(new Date());
        etudiant.setNumTel("test");

        // Mock repository save method
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant addedEtudiant = etudiantService.create(etudiant);

        // Then
        // Verify that the repository's save method was called with the expected etudiant
        verify(etudiantRepository).save(etudiant);

        // Assert the result
        assertNotNull(addedEtudiant);
    }

}