package com.khalil.eschool.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khalil.eschool.model.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentDto {

    private Integer id ;

    private String nomParent;


    private String prenomParent;


    private AdresseDto adresse;


    private String date;


    private String email;


    private String numTel;


    private String cin;


    private String photo;

    @JsonIgnore
    private List<EtudiantDto> etudiants;

    public static ParentDto fromEntity(Parent parent) {
        if (parent == null) {
            return null;
        }

        return ParentDto.builder()
                .id(parent.getId())
                .adresse(AdresseDto.fromEntity(parent.getAdresse()))
                .date(parent.getDate())
                .email(parent.getEmail())
                .nomParent(parent.getNomParent())
                .prenomParent(parent.getPrenomParent())
                .photo(parent.getPhoto())
                .cin(parent.getCin())
                .numTel(parent.getNumTel())
                .build();
    }

    public static Parent toEntity(ParentDto parentDto) {
        if (parentDto == null) {
            return null;
        }
        Parent parent = new Parent();
        parent.setId(parentDto.getId());
        parent.setNomParent(parentDto.getNomParent());
        parent.setPrenomParent(parentDto.getPrenomParent());
        parent.setDate(parentDto.getDate());
        parent.setEmail(parentDto.getEmail());
        parent.setAdresse(AdresseDto.toEntity(parentDto.getAdresse()));
        parent.setCin(parentDto.getCin());
        parent.setPhoto(parentDto.getPhoto());
        parent.setNumTel(parentDto.getNumTel());

        return parent;
    }

}

