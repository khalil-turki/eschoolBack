package com.khalil.eschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Classe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDto {

    private Integer id ;


    private String nomClasse;


    private String specialite;

    @JsonIgnore
    private  List<ProfesseurDto> professeurs;

    @JsonIgnore
    private List<EtudiantDto> etudiants;



    public static ClasseDto fromEntity(Classe classe) {
        if (classe == null) {
            return null;
            // TODO throw an exception
        }

        return ClasseDto.builder()
                .id(classe.getId())
                .nomClasse(classe.getNomClasse())
                .specialite(classe.getSpecialite())
                .build();
    }


    public static Classe toEntity(ClasseDto classeDto) {
        if (classeDto == null)
        {
            return null;
            // TODO throw an exception
        }
        Classe classe = new Classe();
        classe.setId(classeDto.getId());
        classe.setNomClasse(classeDto.getNomClasse());
        classe.setSpecialite(classeDto.getSpecialite());

        return classe;
    }



}
