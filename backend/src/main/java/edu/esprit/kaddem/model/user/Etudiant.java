package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Values.ROLE_ETUDIANT)
public class Etudiant extends Utilisateur {

    @ManyToMany()
    private List<Parent> parents = new ArrayList<>();

    @ManyToOne
    private Classe classe = null;

    @ManyToOne
    private Ecole ecole = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Etudiant etudiant = (Etudiant) o;
        return getId() != null && Objects.equals(getId(), etudiant.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
