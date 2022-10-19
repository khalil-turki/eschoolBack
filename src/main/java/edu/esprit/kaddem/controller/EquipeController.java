package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.EcoleDto;
import edu.esprit.kaddem.dto.EquipeDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.Equipe;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/equipes", produces = "application/json")
public class EquipeController extends AbstractCrudController<Equipe, EquipeDto> {

}
