package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Ecole;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class Utilisateur extends AbstractEntity<Utilisateur> {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @Column(name = "motdepasse")
    private String moteDePasse;

    @OneToOne
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idecole")
    private Ecole ecole;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    private Role role;
}
