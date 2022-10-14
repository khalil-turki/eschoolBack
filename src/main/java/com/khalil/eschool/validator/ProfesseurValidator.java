package com.khalil.eschool.validator;

import com.khalil.eschool.dto.ProfesseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfesseurValidator {  public static List<String> validate(ProfesseurDto professeurDto) {
    List<String> errors = new ArrayList<>();

    if (professeurDto == null) {
        errors.add("Veuillez renseigner le nom d'utilisateur");
        errors.add("Veuillez renseigner le prenom d'utilisateur");
        errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        errors.add("Veuillez renseigner l'adresse d'utilisateur");
        errors.add("Veuillez renseigner le numero de telephone");
        errors.add("Veuillez renseigner une photo");
        errors.addAll(AdresseValidator.validate(null));
        return errors;
    }

    if (!StringUtils.hasLength(professeurDto.getNomProfesseur())) {
        errors.add("Veuillez renseigner le nom");
    }
    if (!StringUtils.hasLength(professeurDto.getPrenomProfesseur())) {
        errors.add("Veuillez renseigner le prenom ");
    }
    if (!StringUtils.hasLength(professeurDto.getEmail())) {
        errors.add("Veuillez renseigner l'email");
    }
    if (!StringUtils.hasLength(professeurDto.getCin())) {
        errors.add("Veuillez renseigner le mot de passe ");
    }
    if (!StringUtils.hasLength(professeurDto.getNumTel())) {
        errors.add("Veuillez renseigner le numero de telephone");
    }
    if (!StringUtils.hasLength(professeurDto.getPhoto())) {
        errors.add("Veuillez renseigner une photo");
    }


    if (professeurDto.getDate() == null) {
        errors.add("Veuillez renseigner la date de naissance d'utilisateur");
    }
    errors.addAll(AdresseValidator.validate(professeurDto.getAdresse()));

    return errors;
}
}
