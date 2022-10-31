package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.Classe;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * A DTO for the {@link Classe} entity
 */

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ClasseDto extends AbstractDto<Classe> implements Serializable {
    private Integer id;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String nomClasse;
    private String specialite;
    private List<ProfesseurDto> professeurs;
}