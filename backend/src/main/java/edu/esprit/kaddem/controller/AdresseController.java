package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.AdresseDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Adresse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/adresses", produces = "application/json")
public class AdresseController extends AbstractCrudController<Adresse, AdresseDto> {

}
