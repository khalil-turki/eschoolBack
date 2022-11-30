package edu.esprit.kaddem.services;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.lib.AbstractCrudService;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.repository.ClasseRepository;
import edu.esprit.kaddem.repository.EtudiantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class EtudiantService extends AbstractCrudService<Etudiant> {

    @Autowired
    private EtudiantRepository etudiantRepository;


    public List<Etudiant> findAllEtudiantsByIdClasse(Integer idClasse) {

        if (idClasse == null) {
            log.error("classe ID is null");

        }

        return etudiantRepository.findEtudiantsByClasseId(idClasse) ;

    }
}
