package edu.esprit.kaddem.services;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.utils.JwtTokenUtil;
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
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public Utilisateur loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Utilisateur> userAuth = userRepository.findByEmail(email);
        if (userAuth.isEmpty()) {
            throw new EntityNotFoundException("Could not find Utilisateur with this email " + email);
        }
        return userAuth.get();
    }

    public String getToken(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(token); // throws exceptions if anything goes wrong.
        Utilisateur userDetails = loadUserByUsername(email);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtTokenUtil.generateToken(userDetails);
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
        EmailService.SendEmail(email,
                "To complete the password reset process, please click here: http://localhost:4200/pages/authentication/reset-password-v2/" + resetToken,
                "Complete password reset");
    }

    public String refreshToken(Map<String, Object> expectedMap, String sub) {
        return jwtTokenUtil.doGenerateRefreshToken(expectedMap, sub);
    }

    @SneakyThrows
    public Utilisateur resetPassword(String email, String newPassword, String token) throws UsernameNotFoundException {

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
        var registered = etudiantService.create(etudiant);
        EmailService.SendEmail(registered.getEmail(), "Welcome to Kaddem", "Welcome to Kaddem");
        return registered;
    }
}
