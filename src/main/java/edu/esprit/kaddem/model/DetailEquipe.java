package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class DetailEquipe extends AbstractEntity<DetailEquipe> implements Serializable {
    @Column
    private Long salle;
    @Column
    private String thematique;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailEquipe that)) return false;
        return getId().equals(that.getId()) && getSalle().equals(that.getSalle()) && getThematique().equals(that.getThematique());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSalle(), getThematique());
    }
}
