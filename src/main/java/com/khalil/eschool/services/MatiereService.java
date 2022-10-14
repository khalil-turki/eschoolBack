package com.khalil.eschool.services;

import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.dto.MatiereDto;

import java.util.List;

public interface MatiereService {
    MatiereDto save(MatiereDto matiereDto);

    MatiereDto findById(Integer id);

    void delete(Integer id);


    List<MatiereDto> findAll();
}
