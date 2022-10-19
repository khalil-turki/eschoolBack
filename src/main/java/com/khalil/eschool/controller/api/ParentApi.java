package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.MatiereDto;
import com.khalil.eschool.dto.ParentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("parent")
public interface ParentApi {

    @PostMapping(value = APP_ROOT+ "/parent/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un parent", notes = "Cette methode permet d'enregistrer ou modifier un parent", response = ParentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet parent cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet parent n'est pas valide")
    })
    ParentDto save(@RequestBody ParentDto parentDto);

    @GetMapping(value = APP_ROOT + "/parent/{idParent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un parent par ID", notes = "Cette methode permet de chercher un parent par son ID", response = ParentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le parent a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun parent n'existe dans la BDD avec l'ID fourni")
    })
    ParentDto findById(@PathVariable("idParent")Integer id);

    @DeleteMapping(value = APP_ROOT + "/parent/delete/{idParent}")
    @ApiOperation(value = "Supprimer un parent", notes = "Cette methode permet de supprimer un parent par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le parent a ete supprime")
    })
    void delete(@PathVariable("idParent") Integer id);

    @GetMapping(value = APP_ROOT + "/parent/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des parents", notes = "Cette methode permet de chercher et renvoyer la liste des parents qui existent "
            + "dans la BDD", responseContainer = "List<ParentDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des parents / Une liste vide")
    })
    List<ParentDto> findAll();
}
