package com.khalil.eschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id ;


    private String nom;


    private String prenom;


    private String email;


    private String numTel;


    private Instant dateDeNaissance;


    private String moteDePasse;


    private AdresseDto adresse;


    private String photo;


    private EcoleDto ecole;

    @JsonIgnore
    private List<RolesDto> roles;



    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .email(utilisateur.getEmail())
                .moteDePasse(utilisateur.getMoteDePasse())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .photo(utilisateur.getPhoto())
                .numTel(utilisateur.getNumTel())
                .ecole(EcoleDto.fromEntity(utilisateur.getEcole()))

                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setNumTel(utilisateurDto.getNumTel());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMoteDePasse(utilisateurDto.getMoteDePasse());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setEcole(EcoleDto.toEntity(utilisateurDto.getEcole()));
        return utilisateur;
    }

}

