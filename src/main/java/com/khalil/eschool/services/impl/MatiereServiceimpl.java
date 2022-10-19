package com.khalil.eschool.services.impl;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.EcoleDto;
import com.khalil.eschool.dto.MatiereDto;
import com.khalil.eschool.exception.EntityNotFoundException;
import com.khalil.eschool.exception.ErrorCodes;
import com.khalil.eschool.exception.InvalidEntityException;
import com.khalil.eschool.repository.ClasseRepository;
import com.khalil.eschool.repository.MatiereRepository;
import com.khalil.eschool.services.MatiereService;
import com.khalil.eschool.validator.EcoleValidator;
import com.khalil.eschool.validator.MatiereValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)

public class MatiereServiceimpl implements MatiereService {
    private MatiereRepository matiereRepository;
    @Autowired
    public MatiereServiceimpl(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    @Override
    public MatiereDto save(MatiereDto matiereDto) {
        List<String> errors = MatiereValidator.validate(matiereDto);
        if (!errors.isEmpty()) {
            log.error("Matiere is not valid {}", matiereDto);
            throw new InvalidEntityException("La matiere n'est pas valide", ErrorCodes.MATIERE_NOT_VALID, errors);
        }

        return MatiereDto.fromEntity(
                matiereRepository.save(
                        MatiereDto.toEntity(matiereDto)
                )
        );
    }

    @Override
    public MatiereDto findById(Integer id) {
        if (id == null) {
            log.error("Matiere ID is null");
            return null;
        }

        return matiereRepository.findById(id).map(MatiereDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Matiere avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.MATIERE_NOT_FOUND)
        );
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Matiere ID is null");
            return;
        }

        matiereRepository.deleteById(id);

    }

    @Override
    public List<MatiereDto> findAll() {
        return matiereRepository.findAll().stream()
                .map(MatiereDto::fromEntity)
                .collect(Collectors.toList());
    }
}
