package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ProfesseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface ProfesseurApi {

    @PostMapping(value = APP_ROOT+ "/professeur/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ProfesseurDto save(@RequestBody ProfesseurDto professeurDto);

    @GetMapping(value = APP_ROOT + "/professeur/{idProfesseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfesseurDto findById(@PathVariable("idProfesseur") Integer id);

    @GetMapping(value = APP_ROOT + "/professeur/delete/{idProfesseur}")
    void delete(@PathVariable("idProfesseur") Integer id);

    @GetMapping(value = APP_ROOT + "/professeur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProfesseurDto> findAll();
}
