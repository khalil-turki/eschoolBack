package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends AbstractRepository<Utilisateur>, JpaSpecificationExecutor<Utilisateur> {
    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmail(String email);
}
