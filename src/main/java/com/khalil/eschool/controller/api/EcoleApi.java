package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.EcoleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface EcoleApi {

    @PostMapping(value = APP_ROOT+ "/ecole/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EcoleDto save(@RequestBody  EcoleDto ecoleDto);

    @GetMapping(value = APP_ROOT + "/ecole/{idEcole}", produces = MediaType.APPLICATION_JSON_VALUE)
    EcoleDto findById(@PathVariable("idEcole") Integer id);

    @GetMapping(value = APP_ROOT + "/ecole/delete/{idEcole}")
    void delete(@PathVariable("idEcole") Integer id);

    @GetMapping(value = APP_ROOT + "/ecole/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EcoleDto> findAll();
}
