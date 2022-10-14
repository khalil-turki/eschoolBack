package com.khalil.eschool.repository;

import com.khalil.eschool.model.Etudiant;
import com.khalil.eschool.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere,Integer > {
}
