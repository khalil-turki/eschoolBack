package com.khalil.eschool.services;

import com.khalil.eschool.dto.EcoleDto;
import com.khalil.eschool.dto.EtudiantDto;

import java.util.List;

public interface EcoleService {

    EcoleDto save(EcoleDto ecoleDto);

    EcoleDto findById(Integer id);

    void delete(Integer id);


    List<EcoleDto> findAll();
}
