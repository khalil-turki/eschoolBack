package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Equipe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquipeRepository extends AbstractRepository<Equipe>, JpaSpecificationExecutor<Equipe> {
}