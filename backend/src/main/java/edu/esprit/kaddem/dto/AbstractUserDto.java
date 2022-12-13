package edu.esprit.kaddem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public abstract class AbstractUserDto<T extends Utilisateur> extends AbstractDto<Utilisateur> {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String avatar;
    private String numTel;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDeNaissance;
    private String plainPassword;
    private String role;
    private Boolean enabled;
    private Boolean isUsing2FA;
}
