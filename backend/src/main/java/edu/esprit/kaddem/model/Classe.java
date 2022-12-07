package edu.esprit.kaddem.model;


import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Professeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "classe")
public class Classe extends AbstractEntity<Classe> {

    @Column(name = "nomclasse")
    private String nomClasse;

    @Column(name = "specialite")
    private String specialite;

    @ManyToMany
    private List<Professeur> professeurs;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.MERGE)
    private List<Etudiant> etudiants;
}
