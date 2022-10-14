package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.MatiereDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface MatiereApi {

    @PostMapping(value = APP_ROOT+ "/matiere/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    MatiereDto save(@RequestBody MatiereDto matiereDto);

    @GetMapping(value = APP_ROOT + "/matiere/{idMatiere}", produces = MediaType.APPLICATION_JSON_VALUE)
    MatiereDto findById(@PathVariable("idMatiere") Integer id);

    @GetMapping(value = APP_ROOT + "/matiere/delete/{idMatiere}")
    void delete(@PathVariable("idMatiere") Integer id);

    @GetMapping(value = APP_ROOT + "/matiere/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MatiereDto> findAll();

}
