package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Values.ROLE_ETUDIANT)
public class Etudiant extends Utilisateur {
    @ManyToOne
    private Parent parent;

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private Ecole ecole;
}
