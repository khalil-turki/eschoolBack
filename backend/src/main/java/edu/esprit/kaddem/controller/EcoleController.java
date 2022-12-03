package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.ClasseDto;
import edu.esprit.kaddem.dto.EcoleDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://127.0.0.1:4200")

@Api
@RestController
@RequestMapping(value = "/ecoles", produces = "application/json")
public class EcoleController extends AbstractCrudController<Ecole, EcoleDto> {

}
