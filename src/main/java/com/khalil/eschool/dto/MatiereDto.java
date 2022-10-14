package com.khalil.eschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Matiere;
import com.khalil.eschool.model.Professeur;
import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class MatiereDto {
    private Integer id ;

    private String nom;

    private String description;

    @JsonIgnore
    private List<Professeur> professeurs;


    public static MatiereDto fromEntity(Matiere matiere) {
        if (matiere == null) {
            return null;
            // TODO throw an exception
        }

        return MatiereDto.builder()
                .id(matiere.getId())
                .nom(matiere.getNom())
                .description(matiere.getDescription())
                .build();
    }


    public static Matiere toEntity(MatiereDto matiereDto) {
        if (matiereDto == null)
        {
            return null;
            // TODO throw an exception
        }
        Matiere matiere = new Matiere();
        matiere.setId(matiereDto.getId());
        matiere.setDescription(matiere.getDescription());
        matiere.setNom(matiere.getNom());

        return matiere;
    }



}
