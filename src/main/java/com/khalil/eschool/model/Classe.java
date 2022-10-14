package com.khalil.eschool.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "classe")
public class Classe extends AbstractEntity{

    @Column(name = "nomclasse")
    private String nomClasse;

    @Column(name = "specialite")
    private String specialite;

    @ManyToMany
    private List<Professeur> professeurs;

    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;




}
