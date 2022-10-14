package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.EtudiantDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface EtudiantApi {

    @PostMapping(value = APP_ROOT+ "/etudiant/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EtudiantDto save(@RequestBody EtudiantDto etudiantDto);

    @GetMapping(value = APP_ROOT + "/etudiant/{idEtudiant}", produces = MediaType.APPLICATION_JSON_VALUE)
    EtudiantDto findById(@PathVariable("idEtudiant") Integer id);

    @GetMapping(value = APP_ROOT + "/etudiant/{nomEtudiant}", produces = MediaType.APPLICATION_JSON_VALUE)
    EtudiantDto findByNom(@PathVariable("nomEtudiant")String nomEtudiant);

    @GetMapping(value = APP_ROOT + "/etudiant/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EtudiantDto> findAll();

    @GetMapping(value = APP_ROOT + "/etudiant/delete/{idEtudiant}")
    void delete(@PathVariable("idEtudiant") Integer id);



    List<EtudiantDto> findAllEtudiantByIdClasse(Integer idClasse);


}
