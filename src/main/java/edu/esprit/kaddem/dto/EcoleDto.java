package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.dto.AdresseDto;
import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.Ecole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link Ecole} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EcoleDto extends AbstractDto<Ecole> implements Serializable {
    private Integer id;
    private String nom;
    private String description;
    private AdresseDto adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String steWeb;
}