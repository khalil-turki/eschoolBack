package edu.esprit.kaddem.utils;

import edu.esprit.kaddem.dto.*;
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
            return mapper.map(user, AdminDto.class);
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }

    public static Utilisateur getUserFromDto(AbstractUserDto<?> userDto){
        if (EtudiantDto.class.equals(userDto.getClass())) {
            return mapper.map(userDto, Etudiant.class);
        } else if (ProfesseurDto.class.equals(userDto.getClass())) {
            return mapper.map(userDto, Professeur.class);
        } else if (ParentDto.class.equals(userDto.getClass())) {
            return mapper.map(userDto, Parent.class);
        } else if (AdminDto.class.equals(userDto.getClass())) {
            return mapper.map(userDto, Admin.class);
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }

    public static Class<?> getUserClass(Role role){
        switch (role){
            case ROLE_ETUDIANT:
                return Etudiant.class;
            case ROLE_PROFESSEUR:
                return Professeur.class;
            case ROLE_PARENT:
                return Parent.class;
            case ROLE_ADMIN:
                return Admin.class;
            default:
                throw new IllegalArgumentException("Unknown user type");
        }
    }
}
