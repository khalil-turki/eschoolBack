package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.Matiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link edu.esprit.kaddem.model.Matiere} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MatiereDto extends AbstractDto<Matiere> implements Serializable {
    private Integer id;
    private String nom;
    private String description;
    private List<ProfesseurDto> professeurs;
}