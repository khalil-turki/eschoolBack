package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
