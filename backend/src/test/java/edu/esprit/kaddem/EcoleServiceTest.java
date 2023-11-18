package edu.esprit.kaddem;

import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.repository.EcoleRepository;
import edu.esprit.kaddem.services.EcoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EcoleServiceTest {

    @Mock
    private EcoleRepository ecoleRepository;

    @InjectMocks
    private EcoleService ecoleService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testRetrieveAllEcoles() {
        log.info("testRetrieveAllEcoles");

        // Create an Ecole object
        Ecole ecole = new Ecole();
        ecole.setNom("Sample Ecole");
        ecole.setDescription("Sample Description");
        ecole.setAdresse(new Adresse("123 Street", "City", "12345", "Country", "State"));
        ecole.setCodeFiscal("ABC123");
        ecole.setPhoto("sample.jpg");
        ecole.setEmail("sample@example.com");
        ecole.setNumTel("123-456-7890");
        ecole.setSteWeb("http://www.sample-ecole.com");

        // Mock the repository to return the sample Ecole when findAll is called
        Mockito.when(ecoleRepository.findAll()).thenReturn(Collections.singletonList(ecole));

        // Call the service method
        List<Ecole> result = ecoleService.getAll();

        // Verify that findAll method was called
        Mockito.verify(ecoleRepository).findAll();

        // Assertions
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(ecole, result.get(0));
    }

    @Test
    public void testAddEcole(){
        log.info("testAddEcole");
        Ecole ecole = new Ecole();
        ecole.setNom("Sample Ecole");
        ecole.setDescription("Sample Description");
        ecole.setAdresse(new Adresse("123 Street", "City", "12345", "Country", "State"));
        ecole.setCodeFiscal("ABC123");
        ecole.setPhoto("sample.jpg");
        ecole.setEmail("sample@example.com");
        ecole.setNumTel("123-456-7890");
        ecole.setSteWeb("http://www.sample-ecole.com");

        Mockito.when(ecoleRepository.save(ecole)).thenReturn(ecole);

        Ecole result = ecoleService.create(ecole);
        Mockito.verify(ecoleRepository).save(ecole);
        Assert.assertNotNull(result);
        Assert.assertEquals(ecole, result);
    }

    @Test
    public  void testRetrieveEcole(){
        log.info("testRetrieveEcole");
        Ecole ecole = new Ecole();
        ecole.setNom("Sample Ecole");
        ecole.setDescription("Sample Description");
        ecole.setAdresse(new Adresse("123 Street", "City", "12345", "Country", "State"));
        ecole.setCodeFiscal("ABC123");
        ecole.setPhoto("sample.jpg");
        ecole.setEmail("sample@example.com");
        ecole.setNumTel("123-456-7890");
        ecole.setSteWeb("http://www.sample-ecole.com");

        Mockito.when(ecoleRepository.findById(1)).thenReturn(java.util.Optional.of(ecole));

        Ecole result = ecoleService.findById(1);
        Mockito.verify(ecoleRepository).findById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals(ecole, result);
    }

    @Test
    public void testDeleteEcole(){
        log.info("testDeleteEcole");
        Ecole ecole = new Ecole();
        ecole.setNom("Sample Ecole");
        ecole.setDescription("Sample Description");
        ecole.setAdresse(new Adresse("123 Street", "City", "12345", "Country", "State"));
        ecole.setCodeFiscal("ABC123");
        ecole.setPhoto("sample.jpg");
        ecole.setEmail("sample@example.com");
        ecole.setNumTel("123-456-7890");
        ecole.setSteWeb("http://www.sample-ecole.com");

        Mockito.lenient().when(ecoleRepository.findById(1)).thenReturn(java.util.Optional.of(ecole));

        ecoleService.delete(ecole);
        Mockito.verify(ecoleRepository).delete(ecole);
    }

    @Test
    public void testUpdateEcole() {
        log.info("testUpdateEcole");

        Ecole ecole = new Ecole();
        ecole.setNom("Sample Ecole");
        ecole.setDescription("Sample Description");
        ecole.setAdresse(new Adresse("123 Street", "City", "12345", "Country", "State"));
        ecole.setCodeFiscal("ABC123");
        ecole.setPhoto("sample.jpg");
        ecole.setEmail("sample@example.com");
        ecole.setNumTel("123-456-7890");
        ecole.setSteWeb("http://www.sample-ecole.com");

        // Mock repository save method
        Mockito.when(ecoleRepository.save(ecole)).thenReturn(ecole);

        // When
        Ecole addedEcole = ecoleService.create(ecole);

        // Then
        // Verify that the repository's save method was called with the expected ecole
        Mockito.verify(ecoleRepository).save(ecole);

        // Assert the result
        Assert.assertNotNull(addedEcole);
    }
}
