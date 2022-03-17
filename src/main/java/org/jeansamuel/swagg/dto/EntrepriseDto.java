package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Adresse;
import org.jeansamuel.swagg.model.Entreprise;

import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private String nom;

    private String Description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String steWeb;

    private Integer idEntreprise;

    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            return null;
        }else{
            return EntrepriseDto.builder()
                    .nom(entreprise.getNom())
                    .Description(entreprise.getDescription())
                    .photo(entreprise.getPhoto())
                    .email(entreprise.getEmail())
                    .numTel(entreprise.getNumTel())
                    .steWeb(entreprise.getSteWeb())
                   /* .idEntreprise(entreprise.getIdEntreprise())*/
                    .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                    .build();
        }
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if(entrepriseDto == null){
            return null;
        }else{
            Entreprise entreprise = new Entreprise();
            entreprise.setNom(entreprise.getNom());
            entreprise.setDescription(entrepriseDto.getDescription());
            entreprise.setPhoto(entrepriseDto.getPhoto());
            entreprise.setEmail(entrepriseDto.getEmail());
            entreprise.setNumTel(entrepriseDto.getNumTel());
            entreprise.setSteWeb(entrepriseDto.getSteWeb());
            /*entreprise.setIdEntreprise(entrepriseDto.getIdEntreprise());*/
            entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));


            return entreprise;
        }
    }

}
