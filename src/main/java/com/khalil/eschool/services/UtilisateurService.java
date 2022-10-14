package com.khalil.eschool.services;

import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Integer id);

    void delete(Integer id);


    List<UtilisateurDto> findAll();
}
