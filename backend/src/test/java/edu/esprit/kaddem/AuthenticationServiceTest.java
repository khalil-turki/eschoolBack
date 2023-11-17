package edu.esprit.kaddem;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.services.AuthenticationService;
import edu.esprit.kaddem.repository.UtilisateurRepository;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UtilisateurRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void loadUserByUsername_UserExists() {
        String email = "test@example.com";
        Utilisateur expectedUser = new Utilisateur();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        Utilisateur actualUser = authenticationService.loadUserByUsername(email);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authenticationService.loadUserByUsername(email));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getToken_SuccessfulAuthentication() {
        String email = "test@example.com";
        String password = "password";
        Utilisateur expectedUser = new Utilisateur();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mock(Authentication.class));
        when(jwtTokenUtil.generateToken(any(Utilisateur.class), anyBoolean())).thenReturn("mockedToken");

        assertDoesNotThrow(() -> authenticationService.getToken(email, password, false));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenUtil, times(1)).generateToken(any(Utilisateur.class), anyBoolean());
    }
}
