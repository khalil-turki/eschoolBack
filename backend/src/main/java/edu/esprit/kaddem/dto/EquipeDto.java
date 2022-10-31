package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.Equipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link edu.esprit.kaddem.model.Equipe} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EquipeDto extends AbstractDto<Equipe> implements Serializable {
    private Integer id;
    private String nomEquipe;
    private Equipe.Niveau niveau;
    private DetailEquipeDto detailEquipe;
}