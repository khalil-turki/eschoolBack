package com.khalil.eschool.repository;

import com.khalil.eschool.model.Etudiant;
import com.khalil.eschool.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent,Integer > {
}
