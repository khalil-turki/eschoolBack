package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Departement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartementRepository extends AbstractRepository<Departement>, JpaSpecificationExecutor<Departement> {
}