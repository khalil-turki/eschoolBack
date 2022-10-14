 package com.khalil.eschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = true)
 @Entity
 @Table(name = "utilisateur")
 public class Utilisateur extends AbstractEntity {

     @Column(name = "nom")
     private String nom;

     @Column(name = "prenom")
     private String prenom;

     @Column(name = "email")
     private String email;

     @Column(name = "numtel")
     private String numTel;

     @Column(name = "datedenaissance")
     private Instant dateDeNaissance;

     @Column(name = "motdepasse")
     private String moteDePasse;

     @Embedded
     private Adresse adresse;

     @Column(name = "photo")
     private String photo;

     @ManyToOne
     @JoinColumn(name = "idecole")
     private Ecole ecole;


     @OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
     @JsonIgnore
     private List<Roles> roles;

 }
