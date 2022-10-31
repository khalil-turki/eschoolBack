package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.EquipeDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Equipe;
import edu.esprit.kaddem.model.user.Professeur;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/professeurs", produces = "application/json")
public class ProfesseurController extends AbstractCrudController<Professeur, ProfesseurDto> {

}
