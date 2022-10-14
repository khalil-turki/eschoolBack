package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.ClasseRepository;
import com.khalil.eschool.repository.EtudiantRepository;
import com.khalil.eschool.services.ClasseService;
import com.khalil.eschool.validator.ClasseValidator;
import com.khalil.eschool.validator.EtudiantValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ClasseServiceimpl implements ClasseService {
    private ClasseRepository classeRepository;

    @Autowired
    public ClasseServiceimpl(ClasseRepository classeRepository ) {
        this.classeRepository=classeRepository;
    }

    @Override
    public ClasseDto save(ClasseDto classeDto) {
        List<String> errors = ClasseValidator.validate(classeDto);
        if (!errors.isEmpty()) {
            log.error("Classe is not valid {}", classeDto);
            throw new InvalidEntityException("La classe n'est pas valide", ErrorCodes.CLASSE_NOT_VALID, errors);
        }

        return ClasseDto.fromEntity(
                classeRepository.save(
                        classeDto.toEntity(classeDto)
                )
        );
    }

    @Override
    public ClasseDto findById(Integer id) {
        if (id == null) {
            log.error("Classe ID is null");
            return null;
        }

        return classeRepository.findById(id).map(ClasseDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Classe avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.CLASSE_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Classe ID is null");
            return;
        }

        classeRepository.deleteById(id);

    }

    @Override
    public List<ClasseDto> findAll() {
        return classeRepository.findAll().stream()
                .map(ClasseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
