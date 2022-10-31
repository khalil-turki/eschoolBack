package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Parent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParentRepository extends AbstractRepository<Parent>, JpaSpecificationExecutor<Parent> {
}