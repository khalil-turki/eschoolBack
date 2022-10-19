package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Professeur;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/etudiants", produces = "application/json")
public class EtudiantController extends AbstractCrudController<Etudiant, EtudiantDto> {

}
