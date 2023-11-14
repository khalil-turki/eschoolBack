package edu.esprit.kaddem.services;

import edu.esprit.kaddem.model.DetailEquipe;
import edu.esprit.kaddem.model.Equipe;
import edu.esprit.kaddem.repository.EquipeRepository;
import edu.esprit.kaddem.services.EquipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeTest {

    @InjectMocks
    private EquipeService equipeService;

    @Mock
    private EquipeRepository equipeRepository;



    @Test
    public void testGetAllEquipe() {
        // Arrange
        List<Equipe> expectedEcoles = Arrays.asList(new Equipe(/* initialize with appropriate values */));

        Mockito.when(equipeRepository.findAll()).thenReturn(expectedEcoles);

        // Act
        List<Equipe> resultEcoles = equipeService.getAll();

        // Assert
        assertEquals(expectedEcoles, resultEcoles);
    }
    // Add more test methods as needed for other service methods
}
