package com.khalil.eschool.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "etudiant")
public class Etudiant extends AbstractEntity{
    @Column(name = "nometudiant")
    private String nomEtudiant;

    @Column(name = "prenometudiant")
    private String prenomEtudiant;

    @Embedded
    private Adresse adresse;

    @Column(name = "date")
    private String date;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idparent")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "idclasse")
    private Classe classe;

}
