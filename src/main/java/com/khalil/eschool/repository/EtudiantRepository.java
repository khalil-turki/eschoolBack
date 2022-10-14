package com.khalil.eschool.repository;

import com.khalil.eschool.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer > {
    Optional<Etudiant> findEtudiantByNomEtudiant(String codeEtudiant);
}
