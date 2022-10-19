package com.khalil.eschool.services.impl;

import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.services.EtudiantService;
import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.validator.EtudiantValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.khalil.eschool.repository.EtudiantRepository;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j




public class EtudiantServiceimpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;

    @Autowired
    public EtudiantServiceimpl(EtudiantRepository etudiantRepository) {this.etudiantRepository=etudiantRepository;}





    @Override
    public EtudiantDto save(EtudiantDto etudiantDto) {
        List<String> errors = EtudiantValidator.validate(etudiantDto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", etudiantDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ETUDIANT_NOT_VALID, errors);
        }

        return EtudiantDto.fromEntity(
                etudiantRepository.save(
                        EtudiantDto.toEntity(etudiantDto)
                )
        );


    }

    @Override
    public EtudiantDto findById(Integer id) {
        if (id == null) {
            log.error("Etudiant ID is null");
            return null;
        }

        return etudiantRepository.findById(id).map(EtudiantDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Etudiant avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ETUDIANT_NOT_FOUND)
        );
    }



    @Override
    public List<EtudiantDto> findAll() {

        return etudiantRepository.findAll().stream()
                .map(EtudiantDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Etudiant ID is null");
            return;
        }

        etudiantRepository.deleteById(id);
    }


    @Override
    public List<EtudiantDto> findAllEtudiantByIdClasse(Integer idClasse) {
        return null;
    }


    @Override
    public EtudiantDto findByNom(String nomEtudiant) {

        if (!StringUtils.hasLength(nomEtudiant)) {
            log.error("nom Etudiant  is null");
            return null;
        }

        return etudiantRepository.findEtudiantByNomEtudiant(nomEtudiant)
                .map(EtudiantDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Aucun Etudiant avec le nom = " + nomEtudiant + " n' ete trouve dans la BDD",
                                ErrorCodes.ETUDIANT_NOT_FOUND)
                );
    }



}
