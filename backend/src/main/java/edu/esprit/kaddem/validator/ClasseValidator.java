package edu.esprit.kaddem.validator;

import edu.esprit.kaddem.dto.ClasseDto;
import edu.esprit.kaddem.model.Classe;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClasseValidator {
    public static List<String> validate(Classe classeDto)
    {
         List<String> errors = new ArrayList<>();


         if (classeDto==null)
            {
            errors.add("veuiillez indiquer le nom de la classe");
            errors.add("veuiillez indiquer la specialité de la classe");
            }

         if (!StringUtils.hasLength(classeDto.getNomClasse()))
            {
                errors.add("veuiillez indiquer le nom de la classe");
            }

         if (!StringUtils.hasLength(classeDto.getSpecialite()))
            {
                errors.add("veuiillez indiquer la specialité de la classe");
            }



        return errors;
    }


}
