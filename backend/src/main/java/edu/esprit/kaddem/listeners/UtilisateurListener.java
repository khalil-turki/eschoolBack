package edu.esprit.kaddem.listeners;

import edu.esprit.kaddem.model.user.Utilisateur;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class UtilisateurListener {

    @Autowired private ObjectFactory<PasswordEncoder> passwordEncoder;

    @PrePersist
    @PreUpdate
    public void onPreUpdate(Object object) {
        if (object instanceof Utilisateur utilisateur && ((Utilisateur) object).getPlainPassword() != null) {
            utilisateur.setPassword(passwordEncoder.getObject().encode(utilisateur.getPlainPassword()));
            utilisateur.setPlainPassword(null);
        }
    }
}
