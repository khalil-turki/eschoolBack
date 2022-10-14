package com.khalil.eschool.validator;

import com.khalil.eschool.dto.ParentDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ParentValidator {

    public static List<String> validate(ParentDto parentDto) {
    List<String> errors = new ArrayList<>();

    if (parentDto == null) {
        errors.add("Veuillez renseigner le nom d'utilisateur");
        errors.add("Veuillez renseigner le prenom d'utilisateur");
        errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        errors.add("Veuillez renseigner l'adresse d'utilisateur");
        errors.add("Veuillez renseigner le numero de telephone");
        errors.add("Veuillez renseigner une photo");
        errors.add("Veuillez renseigner le numero de CIN ");
        errors.addAll(AdresseValidator.validate(null));
        return errors;
    }

    if (!StringUtils.hasLength(parentDto.getNomParent())) {
        errors.add("Veuillez renseigner le nom d'utilisateur");
    }
    if (!StringUtils.hasLength(parentDto.getPrenomParent())) {
        errors.add("Veuillez renseigner le prenom d'utilisateur");
    }
    if (!StringUtils.hasLength(parentDto.getEmail())) {
        errors.add("Veuillez renseigner l'email d'utilisateur");
    }
    if (!StringUtils.hasLength(parentDto.getCin())) {
        errors.add("Veuillez renseigner le mot de passe d'utilisateur");
    }
    if (!StringUtils.hasLength(parentDto.getNumTel())) {
        errors.add("Veuillez renseigner le numero de telephone");
    }
    if (!StringUtils.hasLength(parentDto.getPhoto())) {
        errors.add("Veuillez renseigner une photo");
    }

    if (parentDto.getDate() == null) {
        errors.add("Veuillez renseigner la date de naissance d'utilisateur");
    }
    errors.addAll(AdresseValidator.validate(parentDto.getAdresse()));

    return errors;
}
}
