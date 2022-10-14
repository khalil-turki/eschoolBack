package com.khalil.eschool.services;

import com.khalil.eschool.dto.EtudiantDto;

import java.util.List;


public interface EtudiantService {

    EtudiantDto save(EtudiantDto etudiantDto);

    EtudiantDto findById(Integer id);

    void delete(Integer id);


    List<EtudiantDto> findAll();


    List<EtudiantDto> findAllEtudiantByIdClasse(Integer idClasse);

    EtudiantDto findByNom(String nomEtudiant);



}
