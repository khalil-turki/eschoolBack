package com.khalil.eschool.services;

import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.dto.ProfesseurDto;

import java.util.List;

public interface ProfesseurService {
    ProfesseurDto save(ProfesseurDto professeurDto);

    ProfesseurDto findById(Integer id);

    void delete(Integer id);


    List<ProfesseurDto> findAll();
}
