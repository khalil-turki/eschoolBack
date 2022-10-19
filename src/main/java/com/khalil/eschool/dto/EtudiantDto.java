package com.khalil.eschool.dto;
import com.khalil.eschool.model.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto {

    private Integer id ;

    private String nomEtudiant;


    private String prenomEtudiant;


    private AdresseDto adresse;


    private String date;


    private String email;


    private String photo;


    private ParentDto parent;


    private ClasseDto classe;


    public static EtudiantDto fromEntity(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }

        return EtudiantDto.builder()
                .id(etudiant.getId())
                .adresse(AdresseDto.fromEntity(etudiant.getAdresse()))
                .date(etudiant.getDate())
                .email(etudiant.getEmail())
                .nomEtudiant(etudiant.getNomEtudiant())
                .prenomEtudiant(etudiant.getPrenomEtudiant())
                .photo(etudiant.getPhoto())
                .parent(ParentDto.fromEntity(etudiant.getParent()))
                .classe(ClasseDto.fromEntity(etudiant.getClasse()))
                .build();
    }

    public static Etudiant toEntity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            return null;
        }
        Etudiant etudiant = new Etudiant();
        etudiant.setId(etudiantDto.getId());
        etudiant.setClasse(ClasseDto.toEntity(etudiantDto.getClasse()));
        etudiant.setNomEtudiant(etudiantDto.getNomEtudiant());
        etudiant.setPrenomEtudiant(etudiantDto.getPrenomEtudiant());
        etudiant.setDate(etudiantDto.getDate());
        etudiant.setAdresse(AdresseDto.toEntity(etudiantDto.getAdresse()));
        etudiant.setEmail(etudiantDto.getEmail());
        etudiant.setPhoto(etudiantDto.getPhoto());


        return etudiant;
    }

}
