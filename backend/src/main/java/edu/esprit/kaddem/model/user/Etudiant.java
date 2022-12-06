package edu.esprit.kaddem.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToMany
    private List<Parent> parents = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(columnDefinition="integer", name = "classe_id")
    private Classe classe;

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
