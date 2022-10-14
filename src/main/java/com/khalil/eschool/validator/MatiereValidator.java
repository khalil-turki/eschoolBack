package com.khalil.eschool.validator;

import com.khalil.eschool.dto.MatiereDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MatiereValidator {

    public static List<String> validate(MatiereDto matiereDto)
{
    List<String> errors = new ArrayList<>();


    if (matiereDto==null)
    {
        errors.add("veuiillez indiquer le nom ");
        errors.add("veuiillez indiquer la description");
    }

    if (!StringUtils.hasLength(matiereDto.getNom()))
    {
        errors.add("veuiillez indiquer le nom ");
    }

    if (!StringUtils.hasLength(matiereDto.getDescription()))
    {
        errors.add("veuiillez indiquer adescription");
    }



    return errors;
}


}
