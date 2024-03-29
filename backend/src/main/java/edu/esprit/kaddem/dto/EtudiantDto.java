package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.dto.ClasseDto;
import edu.esprit.kaddem.dto.ParentDto;
import edu.esprit.kaddem.model.user.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Etudiant} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EtudiantDto extends AbstractUserDto<Etudiant> implements Serializable {
    private List<Integer> parentIds;
    private String photo;
    private AdresseDto adresse;
    private ClasseDto classe;
    private ParentDto parent;
}