package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Matiere;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MatiereRepository extends AbstractRepository<Matiere>, JpaSpecificationExecutor<Matiere> {
}