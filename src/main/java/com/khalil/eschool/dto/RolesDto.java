package com.khalil.eschool.dto;

import com.khalil.eschool.model.Roles;
import com.khalil.eschool.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Integer id ;

    private String roleName;

    private Utilisateur utilisateur;

    public static RolesDto fromEntity(Roles role) {
        if (role == null) {
            return null;
            // TODO throw an exception
        }

        return RolesDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .utilisateur(role.getUtilisateur())
                .build();
    }


    public static Roles toEntity(RolesDto roleDto) {
        if (roleDto == null)
        {
            return null;
            // TODO throw an exception
        }
        Roles role = new Roles();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setUtilisateur(roleDto.getUtilisateur());

        return role;
    }



}
