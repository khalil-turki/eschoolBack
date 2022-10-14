package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.EcoleApi;
import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.dto.EcoleDto;
import com.khalil.eschool.services.EcoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EcoleController implements EcoleApi {
    private EcoleService ecoleService ;

    @Autowired
    public EcoleController(EcoleService ecoleService) {
        this.ecoleService = ecoleService;
    }

    @Override
    public EcoleDto save(EcoleDto ecoleDto) {
        return ecoleService.save(ecoleDto);
    }

    @Override
    public EcoleDto findById(Integer id) {
        return ecoleService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        ecoleService.delete(id);
    }

    @Override
    public List<EcoleDto> findAll() {
        return ecoleService.findAll();
    }
}
