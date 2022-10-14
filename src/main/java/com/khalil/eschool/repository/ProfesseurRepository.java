package com.khalil.eschool.repository;

import com.khalil.eschool.model.Etudiant;
import com.khalil.eschool.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur,Integer > {
}
