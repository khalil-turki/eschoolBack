package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Professeur;
import edu.esprit.kaddem.services.ClasseService;
import edu.esprit.kaddem.services.EcoleService;
import edu.esprit.kaddem.services.EtudiantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Api
@RestController
@RequestMapping(value = "/etudiants", produces = "application/json")
public class EtudiantController extends AbstractCrudController<Etudiant, EtudiantDto> {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EcoleService ecoleService;
    @Autowired
    private ClasseService classeService;

    @PostMapping("/affecttoclasse/{etudiantid}/{classeid}")
    public void assignEtudiantToClasse(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("classeid") Integer classeid) {
        Etudiant e = etudiantService.findById(etudiantid);
        Classe c = classeService.findById(classeid);
        e.setClasse(c);
        etudiantService.create(e);
    }

    @PostMapping("/desafectfromclasse/{etudiantid}/{classeid}")
    public void removeEtudiantFromClasse(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("classeid") Integer classeid) {
        Etudiant e = etudiantService.findById(etudiantid);
        e.setClasse(null);
        etudiantService.create(e);
    }

    @PostMapping("/desafectfromecole/{etudiantid}/{ecoleid}")
    public void removeEtudiantFromEcole(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("ecoleid") Integer ecoleid) {
        Etudiant e = etudiantService.findById(etudiantid);
        e.setEcole(null);
        etudiantService.create(e);
    }

    @PostMapping("/affecttoecole/{etudiantid}/{ecoleid}")
    public void assignEtudiantToEcole(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("ecoleid") Integer ecoleid) {
        Etudiant e = etudiantService.findById(etudiantid);
        Ecole c = ecoleService.findById(ecoleid);
        e.setEcole(c);
        etudiantService.create(e);
    }

    @GetMapping("/listByClass/{classeId}")
    public List<EtudiantDto> findEtudiantsByClasseId(@PathVariable("classeId") Integer classeId) {
        var etudiants = etudiantService.findAllEtudiantsByIdClasse(classeId);
        return etudiants.stream().map(this::toDto).toList();
    }

    @GetMapping("/countByClass/{classeId}")
    public int countEtudiantsByClasseId(@PathVariable("classeId") Integer classeId) {
        return etudiantService.countEtudiantsByIdClasse(classeId);
    }



}
