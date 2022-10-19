package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Ecole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EcoleRepository extends AbstractRepository<Ecole>, JpaSpecificationExecutor<Ecole> {
}