package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class Departement extends AbstractEntity<Departement> implements Serializable {
    @Column
    private String nomDepart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departement that)) return false;
        return getId().equals(that.getId()) && getNomDepart().equals(that.getNomDepart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomDepart());
    }
}


