package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Professeur;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfesseurRepository extends AbstractRepository<Professeur>, JpaSpecificationExecutor<Professeur> {
}