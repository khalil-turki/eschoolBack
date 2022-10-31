package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Adresse;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdresseRepository extends AbstractRepository<Adresse>, JpaSpecificationExecutor<Adresse> {
}