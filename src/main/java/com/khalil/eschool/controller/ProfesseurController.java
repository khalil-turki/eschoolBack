package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.controller.api.ProfesseurApi;
import com.khalil.eschool.dto.ProfesseurDto;
import com.khalil.eschool.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfesseurController implements ProfesseurApi {

    private ProfesseurService professeurService;
    @Autowired
    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @Override
    public ProfesseurDto save(ProfesseurDto professeurDto) {
        return professeurService.save(professeurDto);
    }

    @Override
    public ProfesseurDto findById(Integer id) {
        return professeurService.findById(id);
    }

    @Override
    public void delete(Integer id) {
    professeurService.delete(id);
    }

    @Override
    public List<ProfesseurDto> findAll() {
        return professeurService.findAll();
    }
}
