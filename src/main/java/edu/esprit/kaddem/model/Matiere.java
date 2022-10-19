package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.user.Professeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "matiere")
public class Matiere extends AbstractEntity<Matiere> {
    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "matieres")
    private List<Professeur> professeurs;
}