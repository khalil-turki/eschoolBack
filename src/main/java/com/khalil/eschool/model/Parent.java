package com.khalil.eschool.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "parent")
public class Parent extends AbstractEntity{
    @Column(name = "nomparent")
    private String nomParent;

    @Column(name = "prenomparent")
    private String prenomParent;

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

    @OneToMany(mappedBy = "parent")
    private List<Etudiant> etudiants;
}
