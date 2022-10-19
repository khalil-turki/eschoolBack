package com.khalil.eschool.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Ecole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcoleDto {

    private Integer id ;


    private String nom;


    private String description;


    private AdresseDto adresse;


    private String codeFiscal;


    private String photo;


    private String email;


    private String numTel;


    private String steWeb;

    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;


    public static EcoleDto fromEntity(Ecole ecole) {
        if (ecole == null) {
            return null;
            // TODO throw an exception
        }

        return EcoleDto.builder()
                .id(ecole.getId())
                .nom(ecole.getNom())
                .description(ecole.getDescription())
                .adresse(AdresseDto.fromEntity(ecole.getAdresse()))
                .codeFiscal(ecole.getCodeFiscal())
                .photo(ecole.getPhoto())
                .email(ecole.getEmail())
                .numTel(ecole.getNumTel())
                .steWeb(ecole.getSteWeb())
                .build();
    }
    public static Ecole toEntity(EcoleDto ecoleDto) {
        if (ecoleDto == null) {
            return null;
            // TODO throw an exception
        }

        Ecole ecole = new Ecole();
        ecole.setId(ecoleDto.getId());
        ecole.setAdresse(AdresseDto.toEntity(ecoleDto.getAdresse()));
        ecole.setNom(ecoleDto.getNom());
        ecole.setEmail(ecoleDto.getEmail());
        ecole.setDescription(ecoleDto.getDescription());
        ecole.setPhoto(ecoleDto.getPhoto());
        ecole.setCodeFiscal(ecoleDto.getCodeFiscal());
        ecole.setNumTel(ecoleDto.getNumTel());
        ecole.setSteWeb(ecoleDto.getSteWeb());

        return ecole;
    }


}

