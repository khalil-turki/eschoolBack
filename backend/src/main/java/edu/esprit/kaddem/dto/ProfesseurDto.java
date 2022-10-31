package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Parent;
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
public class ProfesseurDto extends AbstractUserDto<Parent> implements Serializable {
    private List<ClasseDto> classes;
    private List<MatiereDto> matieres;
}