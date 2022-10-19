package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.EcoleDto;
import com.khalil.eschool.dto.MatiereDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("matiere")
public interface MatiereApi {

    @PostMapping(value = APP_ROOT+ "/matiere/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une matiere", notes = "Cette methode permet d'enregistrer ou modifier une matiere", response = MatiereDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet matiere cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet matiere n'est pas valide")
    })
    MatiereDto save(@RequestBody MatiereDto matiereDto);

    @GetMapping(value = APP_ROOT + "/matiere/{idMatiere}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une matiere par ID", notes = "Cette methode permet de chercher une matiere par son ID", response = MatiereDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La matiere a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune matiere n'existe dans la BDD avec l'ID fourni")
    })
    MatiereDto findById(@PathVariable("idMatiere") Integer id);

    @DeleteMapping(value = APP_ROOT + "/matiere/delete/{idMatiere}")
    @ApiOperation(value = "Supprimer une matiere", notes = "Cette methode permet de supprimer une matiere par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La matiere a ete supprime")
    })
    void delete(@PathVariable("idMatiere") Integer id);

    @GetMapping(value = APP_ROOT + "/matiere/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des matieres", notes = "Cette methode permet de chercher et renvoyer la liste des matieres qui existent "
            + "dans la BDD", responseContainer = "List<MatiereDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des matieres / Une liste vide")
    })
    List<MatiereDto> findAll();

}
