package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.ParentDto;
import com.khalil.eschool.dto.ProfesseurDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.MatiereRepository;
import com.khalil.eschool.repository.ProfesseurRepository;
import com.khalil.eschool.services.ProfesseurService;
import com.khalil.eschool.validator.ParentValidator;
import com.khalil.eschool.validator.ProfesseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ProfesseurServiceimpl implements ProfesseurService {
    private ProfesseurRepository professeurRepository;
    @Autowired
    public ProfesseurServiceimpl(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public ProfesseurDto save(ProfesseurDto professeurDto) {

        List<String> errors = ProfesseurValidator.validate(professeurDto);
        if (!errors.isEmpty()) {
            log.error("professeur is not valid {}", professeurDto);
            throw new InvalidEntityException("Le professeur n'est pas valide", ErrorCodes.PROFESSEUR_NOT_VALID, errors);
        }

        return ProfesseurDto.fromEntity(
                professeurRepository.save(
                        professeurDto.toEntity(professeurDto)
                )
        );
    }

    @Override
    public ProfesseurDto findById(Integer id) {
        if (id == null) {
            log.error("Professeur ID is null");
            return null;
        }

        return professeurRepository.findById(id).map(ProfesseurDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Professeur avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PROFESSEUR_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Professeur ID is null");
            return;
        }

        professeurRepository.deleteById(id);

    }

    @Override
    public List<ProfesseurDto> findAll() {
        return professeurRepository.findAll().stream()
                .map(ProfesseurDto::fromEntity)
                .collect(Collectors.toList());
    }
}
