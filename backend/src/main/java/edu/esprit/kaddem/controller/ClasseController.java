package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.ClasseDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Classe;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")


@RestController
@RequestMapping(value = "/classes", produces = "application/json")
public class ClasseController extends AbstractCrudController<Classe, ClasseDto> {

}
