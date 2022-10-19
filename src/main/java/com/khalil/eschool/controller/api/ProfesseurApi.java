package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ParentDto;
import com.khalil.eschool.dto.ProfesseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("professeur")
public interface ProfesseurApi {

    @PostMapping(value = APP_ROOT+ "/professeur/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un professeur", notes = "Cette methode permet d'enregistrer ou modifier un professeur", response = ProfesseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet professeur cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet professeur n'est pas valide")
    })
    ProfesseurDto save(@RequestBody ProfesseurDto professeurDto);

    @GetMapping(value = APP_ROOT + "/professeur/{idProfesseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un professeur par ID", notes = "Cette methode permet de chercher un professeur par son ID", response = ProfesseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le professeur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun professeur n'existe dans la BDD avec l'ID fourni")
    })
    ProfesseurDto findById(@PathVariable("idProfesseur") Integer id);

    @DeleteMapping(value = APP_ROOT + "/professeur/delete/{idProfesseur}")
    @ApiOperation(value = "Supprimer un professeur", notes = "Cette methode permet de supprimer un professeur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le professeur a ete supprime")
    })
    void delete(@PathVariable("idProfesseur") Integer id);

    @GetMapping(value = APP_ROOT + "/professeur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des professeurs", notes = "Cette methode permet de chercher et renvoyer la liste des professeurs qui existent "
            + "dans la BDD", responseContainer = "List<ProfesseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des professeurs / Une liste vide")
    })
    List<ProfesseurDto> findAll();
}
