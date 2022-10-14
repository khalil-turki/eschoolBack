package com.khalil.eschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Matiere;
import com.khalil.eschool.model.Professeur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ProfesseurDto {

    private Integer id ;


    private String nomProfesseur;


    private String prenomProfesseur;


    private AdresseDto adresse;


    private String date;


    private String email;


    private String numTel;


    private String cin;


    private String photo;

    @JsonIgnore
    private List<Matiere> matieres;

    @JsonIgnore
    private List<ClasseDto> classes;

    public static ProfesseurDto fromEntity(Professeur professeur) {
        if (professeur == null) {
            return null;
        }

        return ProfesseurDto.builder()
                .id(professeur.getId())
                .adresse(AdresseDto.fromEntity(professeur.getAdresse()))
                .date(professeur.getDate())
                .email(professeur.getEmail())
                .nomProfesseur(professeur.getNomProfesseur())
                .prenomProfesseur(professeur.getPrenomProfesseur())
                .photo(professeur.getPhoto())
                .cin(professeur.getCin())
                .numTel(professeur.getNumTel())
                .build();
    }

    public static Professeur toEntity(ProfesseurDto professeurDto) {
        if (professeurDto == null) {
            return null;
        }
        Professeur professeur = new Professeur();

        professeur.setId(professeurDto.getId());
        professeur.setAdresse(AdresseDto.toEntity(professeurDto.getAdresse()));
        professeur.setCin(professeurDto.getCin());
        professeur.setDate(professeurDto.getDate());
        professeur.setEmail(professeurDto.getEmail());
        professeur.setPhoto(professeurDto.getPhoto());
        professeur.setNumTel(professeurDto.getNumTel());
        professeur.setPrenomProfesseur(professeurDto.getPrenomProfesseur());
        professeur.setNomProfesseur(professeurDto.getNomProfesseur());

        return professeur;
    }

}


