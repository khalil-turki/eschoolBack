package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Contrat;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContratRepository extends AbstractRepository<Contrat>, JpaSpecificationExecutor<Contrat> {
}