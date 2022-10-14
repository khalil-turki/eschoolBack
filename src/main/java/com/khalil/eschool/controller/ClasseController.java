package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.ClasseApi;
import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.services.ClasseService;
import com.khalil.eschool.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClasseController implements ClasseApi {

    private ClasseService classeService ;

    @Autowired
    public ClasseController(ClasseService classeService) {
        this.classeService=classeService;
    }

    @Override
    public ClasseDto save(ClasseDto classeDto) {
        return classeService.save(classeDto);
    }

    @Override
    public ClasseDto findById(Integer id) {
        return classeService.findById(id);
    }

    @Override
    public void delete(Integer id) {
         classeService.delete(id);

    }

    @Override
    public List<ClasseDto> findAll() {
        return classeService.findAll();
    }
}
