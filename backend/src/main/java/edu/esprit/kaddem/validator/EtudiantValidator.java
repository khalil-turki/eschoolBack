package edu.esprit.kaddem.validator;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.model.user.Etudiant;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EtudiantValidator {public static List<String> validate(Etudiant etudiantDto) {
    List<String> errors = new ArrayList<>();

    if (etudiantDto == null) {
        errors.add("Veuillez renseigner le nom de l'etudiant");
        errors.add("Veuillez renseigner le prenom de l'etudiant");
        errors.add("Veuillez renseigner le mot de passe de l'etudiant");
        errors.add("Veuillez renseigner l'adresse de l'etudiant");
        errors.add("Veuillez renseigner l'Email de l'etudiant");
        errors.add("Veuillez renseigner le numero de telephone de l'etudiant");
        errors.addAll(AdresseValidator.validate(null));
        return errors;
    }

    if (!StringUtils.hasLength(etudiantDto.getNom())) {
        errors.add("Veuillez renseigner le Nom d'utilisateur");
    }
    if (!StringUtils.hasLength(etudiantDto.getPrenom())) {
        errors.add("Veuillez renseigner le Prenom d'utilisateur");
    }
    if (!StringUtils.hasLength(etudiantDto.getEmail())) {
        errors.add("Veuillez renseigner l'Email");
    }

    if (etudiantDto.getClass() == null) {
        errors.add("Veuillez renseigner la Classe");
    }
    if (etudiantDto.getDateDeNaissance() == null) {
        errors.add("Veuillez renseigner la Date de naissance ");
    }

    errors.addAll(AdresseValidator.validate(etudiantDto.getAdresse()));

    return errors;
}

}
