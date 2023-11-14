package edu.esprit.kaddem;

import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Professeur;
import edu.esprit.kaddem.repository.EcoleRepository;
import edu.esprit.kaddem.services.EcoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EcoleServiceTest {

    @Mock
    private EcoleRepository ecoleRepository; // Assuming you have a repository for Ecole

    @InjectMocks
    private EcoleService ecoleService;


    @Test
    public void testGetAllEcoles() {
        // Arrange
        List<Ecole> expectedEcoles = Arrays.asList(new Ecole(/* initialize with appropriate values */));

        Mockito.when(ecoleRepository.findAll()).thenReturn(expectedEcoles);

        // Act
        List<Ecole> resultEcoles = ecoleService.getAll();

        // Assert
        assertEquals(expectedEcoles, resultEcoles);
    }

}
