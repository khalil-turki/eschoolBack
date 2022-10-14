package com.khalil.eschool.services;

import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.dto.ParentDto;

import java.util.List;

public interface ParentService {
    ParentDto save(ParentDto parentDto);

    ParentDto findById(Integer id);

    void delete(Integer id);


    List<ParentDto> findAll();
}
