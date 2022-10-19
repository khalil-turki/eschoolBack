package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.Adresse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link Adresse} entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdresseDto extends AbstractDto<Adresse> implements Serializable {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;
}