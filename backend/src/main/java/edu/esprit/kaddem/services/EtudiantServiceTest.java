//package edu.esprit.kaddem.services;
//
//import edu.esprit.kaddem.exception.ErrorCodes;
//import edu.esprit.kaddem.exception.InvalidEntityException;
//import edu.esprit.kaddem.model.user.Etudiant;
//import edu.esprit.kaddem.repository.EtudiantRepository;
//import edu.esprit.kaddem.validator.EtudiantValidator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EtudiantServiceTest {
//
//    @Mock
//    private EtudiantRepository etudiantRepository;
//
//    @Mock
//    private Logger log;
//
//    @InjectMocks
//    private EtudiantService etudiantService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testCreateValidEtudiant() {
//        // Mock data
//        Etudiant etudiant = new Etudiant();
//        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
//
//        // Call the method
//        Etudiant createdEtudiant = etudiantService.create(etudiant);
//
//        // Verify that the etudiantRepository.save method was called
//        verify(etudiantRepository).save(etudiant);
//
//        // Verify that the returned etudiant is the same as the one passed in
//        assertEquals(etudiant, createdEtudiant);
//    }
//
//    @Test
//    void testCreateInvalidEtudiant() {
//        // Mock data
//        Etudiant etudiant = new Etudiant();
//        List<String> validationErrors = Collections.singletonList("Error message");
//        when(log.isErrorEnabled()).thenReturn(true);
//        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
//        when(EtudiantValidator.validate(etudiant)).thenReturn(validationErrors);
//
//        // Call the method
//        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> etudiantService.create(etudiant));
//
//        // Verify that the exception has the correct error codes
//        assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, exception.getErrorCode());
//        assertEquals(validationErrors, exception.getErrors());
//
//        // Verify that the etudiantRepository.save method was not called
//        verify(etudiantRepository, never()).save(etudiant);
//    }
//
//    // Add more tests for other methods in EtudiantService...
//}
//