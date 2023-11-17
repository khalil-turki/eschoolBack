package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;

import edu.esprit.kaddem.model.DetailEquipe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailEquipeRepository extends AbstractRepository<DetailEquipe>, JpaSpecificationExecutor<DetailEquipe> {
}