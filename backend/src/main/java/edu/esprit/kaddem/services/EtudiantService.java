package edu.esprit.kaddem.services;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.exception.ErrorCodes;
import edu.esprit.kaddem.exception.InvalidEntityException;
import edu.esprit.kaddem.lib.AbstractCrudService;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.repository.ClasseRepository;
import edu.esprit.kaddem.repository.EtudiantRepository;
import edu.esprit.kaddem.validator.EtudiantValidator;
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
        return etudiantRepository.findEtudiantsByClasseId(idClasse);
    }

    public int countEtudiantsByIdClasse(Integer idClasse) {

        if (idClasse == null) {
            log.error("classe ID is null");
        }
        return etudiantRepository.countEtudiantsByClasseId(idClasse);
    }

    @Override
    public Etudiant create(Etudiant entity) {
        List<String> errors = EtudiantValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("etudiant' is not valid {}", entity);
            throw new InvalidEntityException("L'etudiant' n'est pas valide", ErrorCodes.ETUDIANT_NOT_VALID, errors);
        }
        return super.create(entity);
    }
}
