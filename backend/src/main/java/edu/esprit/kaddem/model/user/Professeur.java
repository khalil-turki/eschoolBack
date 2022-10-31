package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.Matiere;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@DiscriminatorValue(Role.Values.ROLE_PROFESSEUR)
public class Professeur extends Utilisateur {
    @Column(name = "cin")
    private String cin;

    @ManyToMany(mappedBy = "professeurs")
    private List<Classe> classes;
    @ManyToMany
    private List<Matiere> matieres;

    @ManyToOne
    private Ecole ecole;

}
