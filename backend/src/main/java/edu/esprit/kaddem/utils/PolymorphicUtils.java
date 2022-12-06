package edu.esprit.kaddem.utils;

import edu.esprit.kaddem.dto.AbstractUserDto;
import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.ParentDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.model.user.*;
import org.modelmapper.ModelMapper;

public class PolymorphicUtils {

    private static ModelMapper mapper = new ModelMapper();

    public static AbstractUserDto<?> getDtoFromUser(Utilisateur user){
        if (Etudiant.class.equals(user.getClass())) {
            return mapper.map(user, EtudiantDto.class);
        } else if (Professeur.class.equals(user.getClass())) {
            return mapper.map(user, ProfesseurDto.class);
        } else if (Parent.class.equals(user.getClass())) {
            return mapper.map(user, ParentDto.class);
        } else if (Admin.class.equals(user.getClass())) {
            return mapper.map(user, AbstractUserDto.class);
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }
}
