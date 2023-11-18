package edu.esprit.kaddem;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.repository.AdresseRepository;
import edu.esprit.kaddem.services.AdresseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AdresseTest {

    @Mock
    private AbstractRepository<Adresse> adresseRepository;

    @InjectMocks
    AdresseService adresseService;




    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testRetrieveAllAdresses() {
        log.info("testRetrieveAllAdresses");

        // Create an Adresse object
        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        // Mock the repository to return the sample Adresse when findAll is called
        Mockito.when(adresseRepository.findAll()).thenReturn(Collections.singletonList(adresse));

        // Call the service method
        List<Adresse> result = adresseService.getAll();

        // Verify that findAll method was called
        Mockito.verify(adresseRepository).findAll();

        // Assertions
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(adresse, result.get(0));
    }

    @Test
    public void testAddAdresse(){
        log.info("testAddAdresse");
        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        Mockito.when(adresseRepository.save(adresse)).thenReturn(adresse);

        Adresse result = adresseService.create(adresse);
        Mockito.verify(adresseRepository).save(adresse);
        Assert.assertNotNull(result);
        Assert.assertEquals(adresse, result);

    }

    @Test
    public  void testRetrieveAdresse(){
        log.info("testRetrieveAdresse");
        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        Mockito.when(adresseRepository.findById(1)).thenReturn(java.util.Optional.of(adresse));

        Adresse result = adresseService.findById(1);
        Mockito.verify(adresseRepository).findById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals(adresse, result);
    }

    @Test
    public void testDeleteAdresse(){
        log.info("testDeleteAdresse");
        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        Mockito.lenient().when(adresseRepository.findById(1)).thenReturn(java.util.Optional.of(adresse));

        adresseService.delete(adresse);
        Mockito.verify(adresseRepository).delete(adresse);
    }
    @Test
    public void testUpdateAdresse() {
        log.info("testUpdateAdresse");



        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        // Mock repository save method
        Mockito.when(adresseRepository.save(adresse)).thenReturn(adresse);

        // When
        Adresse addedAdresse = adresseService.create(adresse);

        // Then
        // Verify that the repository's save method was called with the expected etudiant
        Mockito.verify(adresseRepository).save(adresse);

        // Assert the result
        Assert.assertNotNull(addedAdresse);
    }



}
