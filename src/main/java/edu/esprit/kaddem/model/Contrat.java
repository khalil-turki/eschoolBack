package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Contrat extends AbstractEntity<Contrat> implements Serializable {
    @Column
    private Date dateDebutContrat;
    @Column
    private Date dateFinContrat;
    @Column
    private Specialite specialite;
    @Column
    private Boolean archive;

    public enum Specialite {
        IA,
        RESEAUX,
        CLOUD,
        SECURITE
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contrat contrat)) return false;
        return getId().equals(contrat.getId()) && getDateDebutContrat().equals(contrat.getDateDebutContrat()) && getDateFinContrat().equals(contrat.getDateFinContrat()) && getSpecialite() == contrat.getSpecialite() && getArchive().equals(contrat.getArchive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateDebutContrat(), getDateFinContrat(), getSpecialite(), getArchive());
    }


}
