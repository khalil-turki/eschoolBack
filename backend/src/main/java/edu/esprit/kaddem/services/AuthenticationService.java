package edu.esprit.kaddem.services;

import edu.esprit.kaddem.annotations.Log;
import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Gender;
import edu.esprit.kaddem.model.user.Role;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.utils.JwtTokenUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.esprit.kaddem.repository.UtilisateurRepository;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Optional;


@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private EmailService emailService;


    @Override
    @SneakyThrows
    public Utilisateur loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Utilisateur> userAuth = userRepository.findByEmail(email);
        if (userAuth.isEmpty()) {
            throw new EntityNotFoundException("Could not find Utilisateur with this email " + email);
        }
        return userAuth.get();
    }

    @SneakyThrows
    @Log
    public String getToken(String email, String password, Boolean rememberMe) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(token);
        Utilisateur userDetails = loadUserByUsername(email);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtTokenUtil.generateToken(userDetails, rememberMe);
    }

    @SneakyThrows
    public void forgotPassword(String email) throws UsernameNotFoundException {
        var user = loadUserByUsername(email);
        if (user == null) {
            throw new EntityNotFoundException("Could not found Utilisateur with this email " + email);
        }

        String resetToken = Long.toHexString(Double.doubleToLongBits(Math.random()));
        user.setConfirmationToken(resetToken);
        userRepository.save(user);
        emailService.sendSimpleMessage(email,
                "Complete password reset",
                "To complete the password reset process, please click here: http://localhost:8081/auth/reset-password/" + resetToken
                );
    }

    public String refreshToken(String token) {
        return jwtTokenUtil.issueRefreshToken(token);
    }

    @SneakyThrows
    public Utilisateur resetPassword(String token, String email, String newPassword) throws UsernameNotFoundException {

        var currentAuthenticator = loadUserByUsername(email);
        if (currentAuthenticator == null || !currentAuthenticator.getConfirmationToken().equals(token)) {
            throw new EntityNotFoundException("Could not find Utilisateur with this email " + email);
        }

        String EncPass = passwordEncoder.encode(newPassword);
        currentAuthenticator.setPassword(EncPass);
        currentAuthenticator.setConfirmationToken(null);
        userRepository.save(currentAuthenticator);


        return currentAuthenticator;
    }

    public Etudiant signup(Etudiant etudiant){
        etudiant.setGender(Gender.UNSPECIFIED);
        etudiant.setRole(Role.ROLE_ETUDIANT);
        var registered = etudiantService.create(etudiant);
        emailService.sendSimpleMessage(registered.getEmail(), "Welcome to Kaddem", "Welcome to Kaddem");
        return registered;
    }
}
