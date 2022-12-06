package edu.esprit.kaddem.services;

import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UtilisateurRepository userRepository;

    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

}
