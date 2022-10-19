package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.MatiereDto;
import com.khalil.eschool.dto.ParentDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.MatiereRepository;
import com.khalil.eschool.repository.ParentRepository;
import com.khalil.eschool.services.ParentService;
import com.khalil.eschool.validator.MatiereValidator;
import com.khalil.eschool.validator.ParentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ParentServiceimpl implements ParentService {
    private ParentRepository parentRepository;
    @Autowired
    public ParentServiceimpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public ParentDto save(ParentDto parentDto) {

        List<String> errors = ParentValidator.validate(parentDto);
        if (!errors.isEmpty()) {
            log.error("parent is not valid {}", parentDto);
            throw new InvalidEntityException("Le parent n'est pas valide", ErrorCodes.PARENT_NOT_VALID, errors);
        }

        return ParentDto.fromEntity(
                parentRepository.save(
                        ParentDto.toEntity(parentDto)
                )
        );
    }

    @Override
    public ParentDto findById(Integer id) {
        if (id == null) {
            log.error("Parent ID is null");
            return null;
        }

        return parentRepository.findById(id).map(ParentDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Parent avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PARENT_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Parent ID is null");
            return;
        }

        parentRepository.deleteById(id);

    }

    @Override
    public List<ParentDto> findAll() {
        return parentRepository.findAll().stream()
                .map(ParentDto::fromEntity)
                .collect(Collectors.toList());
    }
}
