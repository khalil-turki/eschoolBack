package edu.esprit.kaddem.lib;

import edu.esprit.kaddem.dto.EtudiantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface AbstractRepository<T extends AbstractEntity<?>> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
