package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.Matiere;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.Values.ROLE_PROFESSEUR)
public class Professeur extends Utilisateur {
    @ManyToMany(mappedBy = "professeurs")
    @ToString.Exclude
    private List<Classe> classes;
    @ManyToMany
    @ToString.Exclude
    private List<Matiere> matieres;

    @ManyToOne
    private Ecole ecole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Professeur that = (Professeur) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
