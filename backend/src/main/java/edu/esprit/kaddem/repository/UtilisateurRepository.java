package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends AbstractRepository<Utilisateur>, JpaSpecificationExecutor<Utilisateur> {
    boolean existsByEmail(String email);
    Utilisateur findByEmail(String email);
}
