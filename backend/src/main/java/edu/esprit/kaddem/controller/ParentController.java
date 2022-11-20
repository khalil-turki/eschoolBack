package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.AdresseDto;
import edu.esprit.kaddem.dto.ParentDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.user.Parent;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@Api
@RestController
@RequestMapping(value = "/parents", produces = "application/json")
public class ParentController extends AbstractCrudController<Parent, ParentDto> {

}
