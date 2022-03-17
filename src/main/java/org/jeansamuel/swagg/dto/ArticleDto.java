package org.jeansamuel.swagg.dto;

import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.model.Category;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class ArticleDto {


    private Integer id;

    private String codeArticle;

    private String designation;

    private String photo;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTVA;

    private BigDecimal prixUnitaireTtc;

    private CategoryDto category;

    private Integer idEntreprise;

    private Instant creationDate;

    private Instant lastModifiedDate;


    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;

        } else {
            return ArticleDto.builder()
                    .id(article.getId())
                  /*  .creationDate(article.getCreationDate())
                    .lastModifiedDate(article.getLastModifiedDate())*/
                    .codeArticle(article.getCodeArticle())
                    .designation(article.getDesignation())
                    .photo(article.getPhoto())
                    .prixUnitaireHt(article.getPrixUnitaireHt())
                    .prixUnitaireTtc(article.getPrixUnitaireTtc())
                    .tauxTVA(article.getTauxTVA())

                    .category(CategoryDto.fromEntity(article.getCategory()))
                    .idEntreprise(article.getIdEntreprise())
                    .build();

        }
    }

    public static Article toEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;

        } else {
            Article article = new Article();

            article.setId(articleDto.getId());
           /* article.setCreationDate(articleDto.getCreationDate());
            article.setLastModifiedDate(articleDto.getLastModifiedDate());*/
            article.setCodeArticle(articleDto.getCodeArticle());
            article.setDesignation(articleDto.getDesignation());
            article.setPhoto(articleDto.getPhoto());
            article.setIdEntreprise(articleDto.getIdEntreprise());
            article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
            article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
            article.setTauxTVA(articleDto.getTauxTVA());
            article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
            return article;
        }
    }


}
