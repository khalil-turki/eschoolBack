package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.lib.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(Role.Values.ROLE_PARENT)

public class Parent extends Utilisateur {

    @Column(name = "cin")
    private String cin;

    @OneToMany(mappedBy = "parent")
    private List<Etudiant> etudiants;
}
