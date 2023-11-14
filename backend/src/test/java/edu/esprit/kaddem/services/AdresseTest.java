package edu.esprit.kaddem.services;

import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.repository.AdresseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AdresseTest {
    @InjectMocks
    private AdresseService adresseService;

    @Mock
    private AdresseRepository adresseRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testRetrieveAllAdresses() {
        log.info("testRetrieveAllAdresses");
        List<Adresse> etudiantList = Collections.singletonList(new Adresse());

        // Mock the repository to return the sample list when findAll is called
        Mockito.when(adresseRepository.findAll()).thenReturn(etudiantList);

        List<Adresse> result = adresseRepository.findAll();

        Assert.assertEquals(1, result.size());
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

        Mockito.when(adresseRepository.findById(1)).thenReturn(java.util.Optional.of(adresse));

        adresseService.delete(adresse);
        Mockito.verify(adresseRepository).delete(adresse);
    }

    @Test
    public void testUpdateAdresse(){
        log.info("testUpdateAdresse");
        Adresse adresse = new Adresse();
        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        Mockito.when(adresseRepository.findById(1)).thenReturn(java.util.Optional.of(adresse));

        adresse.setAdresse1("Adresse1");
        adresse.setAdresse2("Adresse2");
        adresse.setVille("ville");
        adresse.setCodePostale("codePostal");
        adresse.setPays("pays");

        Mockito.when(adresseRepository.save(adresse)).thenReturn(adresse);

        Adresse result = adresseService.update(1,adresse);
        Mockito.verify(adresseRepository).save(adresse);
        Assert.assertNotNull(result);
        Assert.assertEquals(adresse, result);
    }

}
