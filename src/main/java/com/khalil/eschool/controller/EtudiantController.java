package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.dto.EtudiantDto;
import com.khalil.eschool.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EtudiantController implements EtudiantApi {

    private EtudiantService etudiantService ;

     @Autowired
    public EtudiantController(EtudiantService etudiantService)
     {
        this.etudiantService=etudiantService;
     }

    @Override
    public EtudiantDto save(EtudiantDto etudiantDto) {
        return etudiantService.save(etudiantDto);
    }

    @Override
    public EtudiantDto findById(Integer id) {
        return etudiantService.findById(id);
    }

    @Override
    public EtudiantDto findByNom(String nomEtudiant) {
        return etudiantService.findByNom(nomEtudiant);
    }

    @Override
    public List<EtudiantDto> findAll() {
        return etudiantService.findAll();
    }

    @Override
    public void delete(Integer id) {
         etudiantService.delete(id);

    }

    @Override
    public List<EtudiantDto> findAllEtudiantByIdClasse(Integer idClasse) {
        return null;
    }
}
