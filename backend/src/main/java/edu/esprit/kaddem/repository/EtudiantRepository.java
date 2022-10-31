package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Etudiant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EtudiantRepository extends AbstractRepository<Etudiant>, JpaSpecificationExecutor<Etudiant> {
}