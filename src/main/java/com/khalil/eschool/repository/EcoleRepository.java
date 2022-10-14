package com.khalil.eschool.repository;

import com.khalil.eschool.model.Ecole;
import com.khalil.eschool.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcoleRepository extends JpaRepository<Ecole,Integer> {
}
