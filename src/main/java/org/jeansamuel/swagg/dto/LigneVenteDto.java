package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.model.LigneCommandeFournisseur;
import org.jeansamuel.swagg.model.LigneVente;
import org.jeansamuel.swagg.model.Ventes;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {


    private VentesDto ventes;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    private ArticleDto article;


    public static LigneVenteDto fromEntity(LigneVente ligneVente){

        if (ligneVente == null){
            return null;
        }else{
            return LigneVenteDto.builder()
                    .quantite(ligneVente.getQuantite())
                    .prixUnitaire(ligneVente.getPrixUnitaire())
                    .idEntreprise(ligneVente.getIdEntreprise())
                    .ventes(VentesDto.fromEntity(ligneVente.getVentes()))
                    .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                    .build();
        }
    }
    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (ligneVenteDto == null) {
            return null;
        } else {
            LigneVente ligneVente = new LigneVente();
            ligneVente.setQuantite(ligneVenteDto.getQuantite());
            ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
            ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
            ligneVente.setVentes(VentesDto.toEntity(ligneVenteDto.getVentes()));
            ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
            return ligneVente;
        }
    }
}
