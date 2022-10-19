package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public abstract class AbstractUserDto<T extends Utilisateur> extends AbstractDto<Utilisateur> {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private Instant dateDeNaissance;
}
