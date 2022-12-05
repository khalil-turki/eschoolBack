package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.lib.AbstractRepository;
import edu.esprit.kaddem.model.user.Etudiant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EtudiantRepository extends AbstractRepository<Etudiant>, JpaSpecificationExecutor<Etudiant> {
    List<Etudiant> findEtudiantsByClasseId(Integer id);
    int countEtudiantsByClasseId(Integer id);


}