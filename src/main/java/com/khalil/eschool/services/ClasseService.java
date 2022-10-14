package com.khalil.eschool.services;

import com.khalil.eschool.dto.ClasseDto;


import java.util.List;

public interface ClasseService {

    ClasseDto save(ClasseDto classeDto);

    ClasseDto findById(Integer id);

    void delete(Integer id);


    List<ClasseDto> findAll();
}
