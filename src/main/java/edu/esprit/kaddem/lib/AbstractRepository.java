package edu.esprit.kaddem.lib;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

public interface AbstractRepository<T extends AbstractEntity<?>> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
