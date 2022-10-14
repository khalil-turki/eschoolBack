package com.khalil.eschool.validator;

import com.khalil.eschool.dto.EtudiantDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EtudiantValidator {public static List<String> validate(EtudiantDto etudiantDto) {
    List<String> errors = new ArrayList<>();

    if (etudiantDto == null) {
        errors.add("Veuillez renseigner le nom de l'etudiant");
        errors.add("Veuillez renseigner le prenom de l'etudiant");
        errors.add("Veuillez renseigner le mot de passe de l'etudiant");
        errors.add("Veuillez renseigner l'adresse de l'etudiant");
        errors.add("Veuillez renseigner l'Email de l'etudiant");
        errors.add("Veuillez renseigner le numero de telephone de l'etudiant");
        errors.add("Veuillez renseigner une photo de l'etudiant");
        errors.addAll(AdresseValidator.validate(null));
        return errors;
    }

    if (!StringUtils.hasLength(etudiantDto.getNomEtudiant())) {
        errors.add("Veuillez renseigner le Nom d'utilisateur");
    }
    if (!StringUtils.hasLength(etudiantDto.getPrenomEtudiant())) {
        errors.add("Veuillez renseigner le Prenom d'utilisateur");
    }
    if (!StringUtils.hasLength(etudiantDto.getEmail())) {
        errors.add("Veuillez renseigner l'Email");
    }

    if (!StringUtils.hasLength(etudiantDto.getPhoto())) {
        errors.add("Veuillez renseigner une Photo");
    }

    if (etudiantDto.getClasse() == null) {
        errors.add("Veuillez renseigner la Classe");
    }
    if (etudiantDto.getDate() == null) {
        errors.add("Veuillez renseigner la Date de naissance ");
    }
    if (etudiantDto.getParent() == null) {
        errors.add("Veuillez renseigner le Parent");
    }
    errors.addAll(AdresseValidator.validate(etudiantDto.getAdresse()));

    return errors;
}

}
