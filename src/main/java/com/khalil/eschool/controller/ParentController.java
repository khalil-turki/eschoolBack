package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.controller.api.ParentApi;
import com.khalil.eschool.dto.ParentDto;
import com.khalil.eschool.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParentController implements ParentApi {

    private ParentService parentService;
    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @Override
    public ParentDto save(ParentDto parentDto) {
        return parentService.save(parentDto);
    }

    @Override
    public ParentDto findById(Integer id) {
        return parentService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        parentService.delete(id);

    }

    @Override
    public List<ParentDto> findAll() {
        return parentService.findAll();
    }
}
