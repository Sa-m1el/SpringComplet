package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.LigneCommandeClient;


import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){

        if (ligneCommandeClient == null){
            return null;
        }else{
            return LigneCommandeClientDto.builder()

                    .id(ligneCommandeClient.getId())
                    .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                    .quantite(ligneCommandeClient.getQuantite())
                    .idEntreprise(ligneCommandeClient.getIdEntreprise())
                    .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                    .build();
        }
    }
    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto){
        if (ligneCommandeClientDto == null){
            return null;
        }else{
            LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
            ligneCommandeClient.setId(ligneCommandeClientDto.getId());
            ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
            ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
            ligneCommandeClient.setIdEntreprise(ligneCommandeClientDto.getIdEntreprise());
            ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
            return ligneCommandeClient;
        }
    }

}
