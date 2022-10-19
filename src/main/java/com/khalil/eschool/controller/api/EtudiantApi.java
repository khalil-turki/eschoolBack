package com.khalil.eschool.controller.api;

import com.khalil.eschool.dto.EtudiantDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.khalil.eschool.utils.Constants.APP_ROOT;
@Api("etudiant")
public interface EtudiantApi {

    @PostMapping(value = APP_ROOT+ "/etudiant/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un etudiant", notes = "Cette methode permet d'enregistrer ou modifier un etudiant", response = EtudiantDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet etudiant cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet etudiant n'est pas valide")
    })
    EtudiantDto save(@RequestBody EtudiantDto etudiantDto);

    @GetMapping(value = APP_ROOT + "/etudiant/{idEtudiant}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un etudiant par ID", notes = "Cette methode permet de chercher un etudiant par son ID", response = EtudiantDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etudiant a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun etudiant n'existe dans la BDD avec l'ID fourni")
    })
    EtudiantDto findById(@PathVariable("idEtudiant") Integer id);

    @GetMapping(value = APP_ROOT + "/etudiant/{nomEtudiant}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par NOM", notes = "Cette methode permet de chercher un etudiant par son NOM", response =
            EtudiantDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etudiant a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun etudiant n'existe dans la BDD avec le CODE fourni")
    })
    EtudiantDto findByNom(@PathVariable("nomEtudiant")String nomEtudiant);

    @GetMapping(value = APP_ROOT + "/etudiant/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des etudiants", notes = "Cette methode permet de chercher et renvoyer la liste des etudiants qui existent "
            + "dans la BDD", responseContainer = "List<EtudiantDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des article / Une liste vide")
    })
    List<EtudiantDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/etudiant/delete/{idEtudiant}")
    @ApiOperation(value = "Supprimer un etudiant", notes = "Cette methode permet de supprimer un etudiant par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'etudiant a ete supprime")
    })
    void delete(@PathVariable("idEtudiant") Integer id);



    List<EtudiantDto> findAllEtudiantByIdClasse(Integer idClasse);


}
