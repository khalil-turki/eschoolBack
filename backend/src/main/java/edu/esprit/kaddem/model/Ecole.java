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
@Table(name = "ecole")
public class Ecole extends AbstractEntity<Ecole> {

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;

    @Column(name = "codefiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "siteweb")
    private String steWeb;

    @OneToMany(mappedBy = "ecole")
    private List<Etudiant> etudiants;

    @OneToMany(mappedBy = "ecole")
    private List<Professeur> professeurs;

}
