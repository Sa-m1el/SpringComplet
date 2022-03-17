package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.Roles;
import org.jeansamuel.swagg.model.Utilisateur;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String motDePasse;

    private String photo;

    private Integer idEntreprise;

    private AdresseDto adresse;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){

        if (utilisateur == null){
            return null;
        }else{
            return UtilisateurDto.builder()
                    .id(utilisateur.getId())
                    .nom(utilisateur.getNom())
                    .prenom(utilisateur.getPrenom())
                    .dateDeNaissance(utilisateur.getDateDeNaissance())
                    .photo(utilisateur.getPhoto())
                    .email(utilisateur.getEmail())
                    .motDePasse(utilisateur.getMotDePasse())
//                    .idEntreprise(utilisateur.getIdEntreprise())
                    .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                    .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                    .roles(
                            utilisateur.getRoles() != null ?
                                    utilisateur.getRoles().stream()
                                            .map(RolesDto::fromEntity)
                                            .collect(Collectors.toList()) : null
                    )
                    .build();
        }
    }
    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto == null){
            return null;
        }else{
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(utilisateurDto.getNom());
            utilisateur.setPrenom(utilisateurDto.getPrenom());
            utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
            utilisateur.setPhoto(utilisateurDto.getPhoto());
            utilisateur.setEmail(utilisateurDto.getEmail());
            utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
            /*utilisateur.setIdEntreprise(utilisateurDto.getIdEntreprise());*/

            utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
            utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
            utilisateur.setRoles(
                    utilisateurDto.getRoles() != null ?
                            utilisateurDto.getRoles().stream()
                            .map(RolesDto::toEntity)
                                    .collect(Collectors.toList()) : null
            );
            return utilisateur;
        }
    }

}
