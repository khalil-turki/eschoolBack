package edu.esprit.kaddem.services;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import edu.esprit.kaddem.repository.UtilisateurRepository;


@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private EtudiantService etudiantService;

    @Override
    @SneakyThrows
    public Utilisateur loadUserByUsername(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("Could not find user with this email " + email);
        }
        return user;
    }
    public Etudiant save(Etudiant user) {
        return etudiantService.create(user);
    }
}
