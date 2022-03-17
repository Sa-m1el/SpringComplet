package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.LigneCommandeFournisseur;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {

    @JsonIgnore
    private ArticleDto article;

    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseurDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){

        if (ligneCommandeFournisseur == null){
            return null;
        }else{
            return LigneCommandeFournisseurDto.builder()
                    .quantite(ligneCommandeFournisseur.getQuantite())
                    .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                    .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                    .build();
        }
    }
    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (ligneCommandeFournisseurDto == null) {
            return null;
        } else {
            LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
            ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
            ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
            ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
            return ligneCommandeFournisseur;
        }
    }
}
