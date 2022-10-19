package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.ClasseDto;
import com.khalil.eschool.dto.EtudiantDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("classe")
public interface ClasseApi {

    @PostMapping(value = APP_ROOT+ "/classe/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une classe", notes = "Cette methode permet d'enregistrer ou modifier une classe", response = ClasseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet classe cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet classe n'est pas valide")
    })
    ClasseDto save( @RequestBody ClasseDto classeDto);

    @GetMapping(value = APP_ROOT + "/classe/{idClasse}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une classe par ID", notes = "Cette methode permet de chercher une classe par son ID", response = ClasseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La classe a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune classe n'existe dans la BDD avec l'ID fourni")
    })
    ClasseDto findById(@PathVariable("idClasse") Integer id);

    @DeleteMapping(value = APP_ROOT + "/classe/delete/{idClasse}")
    @ApiOperation(value = "Supprimer une classe", notes = "Cette methode permet de supprimer une classe par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La classe a ete supprime")
    })
    void delete(@PathVariable("idClasse") Integer id);

    @GetMapping(value = APP_ROOT + "/classe/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des classes", notes = "Cette methode permet de chercher et renvoyer la liste des classes qui existent "
            + "dans la BDD", responseContainer = "List<ClasseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des classes / Une liste vide")
    })
    List<ClasseDto> findAll();
}
