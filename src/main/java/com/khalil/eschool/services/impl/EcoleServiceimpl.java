package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.EcoleDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.ClasseRepository;
import com.khalil.eschool.repository.EcoleRepository;
import com.khalil.eschool.services.EcoleService;
import com.khalil.eschool.validator.ClasseValidator;
import com.khalil.eschool.validator.EcoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class EcoleServiceimpl implements EcoleService {
    private EcoleRepository ecoleRepository;

    @Autowired
    public EcoleServiceimpl(EcoleRepository ecoleRepository) {
        this.ecoleRepository = ecoleRepository;
    }

    @Override
    public EcoleDto save(EcoleDto ecoleDto) {

        List<String> errors = EcoleValidator.validate(ecoleDto);
        if (!errors.isEmpty()) {
            log.error("Ecole is not valid {}", ecoleDto);
            throw new InvalidEntityException("L'ecole n'est pas valide", ErrorCodes.ECOLE_NOT_VALID, errors);
        }

        return EcoleDto.fromEntity(
                ecoleRepository.save(
                        ecoleDto.toEntity(ecoleDto)
                )
        );
    }

    @Override
    public EcoleDto findById(Integer id) {
        if (id == null) {
            log.error("Ecole ID is null");
            return null;
        }

        return ecoleRepository.findById(id).map(EcoleDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Ecole avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ECOLE_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Ecole ID is null");
            return;
        }

        ecoleRepository.deleteById(id);

    }

    @Override
    public List<EcoleDto> findAll() {
        return ecoleRepository.findAll().stream()
                .map(EcoleDto::fromEntity)
                .collect(Collectors.toList());
    }
}
