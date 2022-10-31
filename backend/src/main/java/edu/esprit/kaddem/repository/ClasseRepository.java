package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.Classe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClasseRepository extends AbstractRepository<Classe>, JpaSpecificationExecutor<Classe>  {
}