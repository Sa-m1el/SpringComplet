package org.jeansamuel.swagg.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstk")
public class MvtStk extends AbstractEntity {

    @Column(name = "quantite")
    private Instant dateMvt;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemvt")
    private TypeMvtStk typeMvt;

    @Column(name = "identreprise")
    private Integer idEntreprise;



}
