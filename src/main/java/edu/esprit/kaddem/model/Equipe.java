package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Equipe extends AbstractEntity<Equipe> implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipe equipe)) return false;
        return getId().equals(equipe.getId()) && getNomEquipe().equals(equipe.getNomEquipe()) && getNiveau() == equipe.getNiveau();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomEquipe(), getNiveau());
    }

    public enum Niveau {
        JUNIOR,
        SENIOR,
        EXPERT
    }

    @Column
    private String nomEquipe;
    @Column
    private Niveau niveau;

    @OneToOne
    private DetailEquipe detailEquipe;
}
