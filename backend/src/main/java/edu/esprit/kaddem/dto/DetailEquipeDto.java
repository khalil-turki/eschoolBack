package edu.esprit.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link edu.esprit.kaddem.model.DetailEquipe} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DetailEquipeDto implements Serializable {
    private Integer id;
    private Long salle;
    private String thematique;
}