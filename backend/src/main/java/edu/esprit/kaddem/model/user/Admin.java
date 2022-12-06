package edu.esprit.kaddem.model.user;

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
@DiscriminatorValue(Role.Values.ROLE_ADMIN)

public class Admin extends Utilisateur {

    @OneToOne
    private Ecole cin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Admin parent = (Admin) o;
        return getId() != null && Objects.equals(getId(), parent.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
