package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Adresse;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends AbstractRepository<Adresse>, JpaSpecificationExecutor<Adresse> {
}