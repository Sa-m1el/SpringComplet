package org.jeansamuel.swagg.dto;


import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.Roles;




@Builder
@Data
public class RolesDto {

    private String roleName;

    private UtilisateurDto utilisateur;

    private Integer idEntreprise;

    public static RolesDto fromEntity(Roles roles){

        if (roles == null){
            return null;
        }else{
            return RolesDto.builder()
                    .roleName(roles.getRoleName())
                    .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                    .idEntreprise(roles.getIdEntreprise())
                    .build();
        }
    }
    public static Roles toEntity(RolesDto rolesDto) {
        if (rolesDto == null) {
            return null;
        } else {
            Roles roles = new Roles();
            roles.setRoleName(rolesDto.getRoleName());
            roles.setIdEntreprise(rolesDto.getIdEntreprise());
            roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));

            return roles;
        }
    }
}
