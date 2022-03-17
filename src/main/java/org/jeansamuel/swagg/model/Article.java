package org.jeansamuel.swagg.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity{

    @Column(name = "codeArticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "photo")
    private String photo;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "prixUnitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtvA")
    private BigDecimal tauxTVA;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

}
