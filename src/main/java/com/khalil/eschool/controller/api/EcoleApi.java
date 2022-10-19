package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.EcoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("ecole")
public interface EcoleApi {

    @PostMapping(value = APP_ROOT+ "/ecole/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une ecole", notes = "Cette methode permet d'enregistrer ou modifier une ecole", response = EcoleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet ecole cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet ecole n'est pas valide")
    })
    EcoleDto save(@RequestBody  EcoleDto ecoleDto);

    @GetMapping(value = APP_ROOT + "/ecole/{idEcole}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une ecole par ID", notes = "Cette methode permet de chercher une ecole par son ID", response = EcoleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La ecole a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune ecole n'existe dans la BDD avec l'ID fourni")
    })
    EcoleDto findById(@PathVariable("idEcole") Integer id);

    @DeleteMapping(value = APP_ROOT + "/ecole/delete/{idEcole}")
    @ApiOperation(value = "Supprimer une ecole", notes = "Cette methode permet de supprimer une ecole par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'ecole a ete supprime")
    })
    void delete(@PathVariable("idEcole") Integer id);

    @GetMapping(value = APP_ROOT + "/ecole/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des ecoles", notes = "Cette methode permet de chercher et renvoyer la liste des ecole qui existent "
            + "dans la BDD", responseContainer = "List<EcoleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ecoles / Une liste vide")
    })
    List<EcoleDto> findAll();
}
