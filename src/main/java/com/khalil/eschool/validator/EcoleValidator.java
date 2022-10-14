package com.khalil.eschool.validator;

import com.khalil.eschool.dto.EcoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EcoleValidator {

    public static List<String> validate(EcoleDto ecoleDto) {

                       List<String> errors = new ArrayList<>();

    if (ecoleDto == null) {
        errors.add("Veuillez renseigner le nom");
        errors.add("Veuillez renseigner  la description ");
        errors.add("Veuillez renseigner  l'Email");
        errors.add("Veuillez renseigner  une Photo ");
        errors.add("Veuillez renseigner l'Email de l'etudiant");
        errors.add("Veuillez renseigner le numero de telephone ");
        errors.add("Veuillez renseigner le site web");
        errors.addAll(AdresseValidator.validate(null));
        return errors;
    }

    if (!StringUtils.hasLength(ecoleDto.getNom())) {
        errors.add("Veuillez renseigner le Nom ");
    }
    if (!StringUtils.hasLength(ecoleDto.getDescription())) {
        errors.add("Veuillez renseigner la description ");
    }
    if (!StringUtils.hasLength(ecoleDto.getEmail())) {
        errors.add("Veuillez renseigner l'Email");
    }

    if (!StringUtils.hasLength(ecoleDto.getPhoto())) {
        errors.add("Veuillez renseigner une Photo");
    }

    if (!StringUtils.hasLength(ecoleDto.getNumTel())) {
        errors.add("Veuillez renseigner le numero tel");
    }
        if (!StringUtils.hasLength(ecoleDto.getCodeFiscal())) {
        errors.add("Veuillez renseigner le code fiscale");
    }
        if (!StringUtils.hasLength(ecoleDto.getSteWeb())) {
        errors.add("Veuillez renseigner le site web");
    }
    errors.addAll(AdresseValidator.validate(ecoleDto.getAdresse()));

    return errors;
}

}
