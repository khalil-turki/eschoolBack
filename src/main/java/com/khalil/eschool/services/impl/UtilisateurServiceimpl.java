package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.ProfesseurDto;
import com.khalil.eschool.dto.UtilisateurDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.ProfesseurRepository;
import com.khalil.eschool.repository.UtilisateurRepository;
import com.khalil.eschool.services.UtilisateurService;
import com.khalil.eschool.validator.ProfesseurValidator;
import com.khalil.eschool.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class UtilisateurServiceimpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
@Autowired
    public UtilisateurServiceimpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()) {
            log.error("utilisateur is not valid {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(
                        UtilisateurDto.toEntity(utilisateurDto)
                )
        );    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }

        return utilisateurRepository.findById(id).map(UtilisateurDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }

        utilisateurRepository.deleteById(id);

    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }
}
