package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.Fournisseur;

import java.util.List;

@Builder
@Data
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;


    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null){
            return null;
        }else{
            return FournisseurDto.builder()
                    .id(fournisseur.getId())
                    .nom(fournisseur.getNom())
                    .prenom(fournisseur.getPrenom())
                    .photo(fournisseur.getPhoto())
                    .mail(fournisseur.getMail())
                    .photo(fournisseur.getPhoto())
                  /*  .idEntreprise(fournisseur.getIdEntreprise())*/
                    .numTel(fournisseur.getNumTel())
                    .build();
        }
    }
    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if(fournisseurDto == null){
            return null;
        }else{
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(fournisseurDto.getId());
            fournisseur.setNom(fournisseurDto.getNom());
            fournisseur.setPrenom(fournisseurDto.getPrenom());
            fournisseur.setPhoto(fournisseurDto.getPhoto());
            fournisseur.setMail(fournisseurDto.getMail());
            /*fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());*/
            fournisseur.setNumTel(fournisseurDto.getNumTel());

            return fournisseur;
        }
    }

}
