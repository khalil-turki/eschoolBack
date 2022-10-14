package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ClasseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface ClasseApi {

    @PostMapping(value = APP_ROOT+ "/classe/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ClasseDto save( @RequestBody ClasseDto classeDto);

    @GetMapping(value = APP_ROOT + "/classe/{idClasse}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClasseDto findById(@PathVariable("idClasse") Integer id);

    @GetMapping(value = APP_ROOT + "/classe/delete/{idClasse}")
    void delete(@PathVariable("idClasse") Integer id);

    @GetMapping(value = APP_ROOT + "/classe/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClasseDto> findAll();
}
