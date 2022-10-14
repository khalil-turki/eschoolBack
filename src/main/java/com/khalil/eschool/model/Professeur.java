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
@Table(name = "professeur")
public class Professeur extends AbstractEntity{

    @Column(name = "nomprofesseur")
    private String nomProfesseur;

    @Column(name = "prenomprofesseur")
    private String prenomProfesseur;

    @Embedded
    private Adresse adresse;

    @Column(name = "date")
    private String date;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "cin")
    private String cin;

    @Column(name = "photo")
    private String photo;

    @ManyToMany(mappedBy = "professeurs")
    private List<Classe> classes;

    @ManyToMany
    private List<Matiere> matieres;



}
