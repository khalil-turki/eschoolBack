package edu.esprit.kaddem.model.user;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Values.ROLE_PARENT)

public class Parent extends Utilisateur {

    @Column(name = "cin")
    private String cin;

    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    private List<Etudiant> etudiants;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Parent parent = (Parent) o;
        return getId() != null && Objects.equals(getId(), parent.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
