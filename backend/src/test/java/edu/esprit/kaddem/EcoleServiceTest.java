package edu.esprit.kaddem;

import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.repository.EcoleRepository;
import edu.esprit.kaddem.services.EcoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EcoleServiceTest {

    @Mock
    private EcoleRepository ecoleRepository; // Assuming you have a repository for Ecole

    @InjectMocks
    private EcoleService ecoleService;

    @Test
    public void testGetAllEcoles() {
        // Arrange
        List<Ecole> expectedEcoles = Arrays.asList(new Ecole());

        when(ecoleRepository.findAll()).thenReturn(expectedEcoles);

        // Act
        List<Ecole> resultEcoles = ecoleService.getAll();

        // Assert
        assertEquals(expectedEcoles, resultEcoles);
    }

    @Test
    public void testCreateEcole() {
        // Arrange
        Ecole newEcole = new Ecole();

        when(ecoleRepository.save(newEcole)).thenReturn(newEcole);

        // Act
        Ecole createdEcole = ecoleService.create(newEcole);

        // Assert
        assertEquals(newEcole, createdEcole);
    }

    @Test
    public void testDeleteEcole() {
        // Arrange
        Ecole existingEcole = new Ecole(/* initialize with appropriate values */);

        // Act
        ecoleService.delete(existingEcole);

        // Assert
        verify(ecoleRepository).delete(existingEcole);
    }

}
