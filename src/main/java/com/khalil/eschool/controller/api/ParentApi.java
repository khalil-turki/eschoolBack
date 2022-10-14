package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ParentDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;

public interface ParentApi {

    @PostMapping(value = APP_ROOT+ "/parent/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ParentDto save(@RequestBody ParentDto parentDto);

    @GetMapping(value = APP_ROOT + "/parent/{idParent}", produces = MediaType.APPLICATION_JSON_VALUE)
    ParentDto findById(@PathVariable("idParent")Integer id);

    @GetMapping(value = APP_ROOT + "/parent/delete/{idParent}")
    void delete(@PathVariable("idParent") Integer id);

    @GetMapping(value = APP_ROOT + "/parent/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ParentDto> findAll();
}
