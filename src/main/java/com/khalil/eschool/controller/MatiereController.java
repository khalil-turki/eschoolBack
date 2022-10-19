package com.khalil.eschool.controller;

import com.khalil.eschool.controller.api.EtudiantApi;
import com.khalil.eschool.controller.api.MatiereApi;
import com.khalil.eschool.dto.MatiereDto;
import com.khalil.eschool.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatiereController implements MatiereApi {

    private MatiereService matiereService;
    @Autowired

public MatiereController(MatiereService matiereService)
    {
        this.matiereService=matiereService;
    }

    @Override
    public MatiereDto save(MatiereDto matiereDto) {

        return matiereService.save(matiereDto);
    }

    @Override
    public MatiereDto findById(Integer id) {
        return matiereService.findById(id);
    }

    @Override
    public void delete(Integer id) {
         matiereService.delete(id);
    }

    @Override
    public List<MatiereDto> findAll() {
        return matiereService.findAll();
    }
}
