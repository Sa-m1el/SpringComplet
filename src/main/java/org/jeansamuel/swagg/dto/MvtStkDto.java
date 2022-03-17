package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.MvtStk;
import org.jeansamuel.swagg.model.TypeMvtStk;


import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Instant dateMvt;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    @JsonIgnore
    private ArticleDto article;

    private TypeMvtStk typeMvt;

    public static MvtStkDto fromEntity(MvtStk mvtStk){

        if (mvtStk == null){
            return null;
        }else{
            return MvtStkDto.builder()
                    .dateMvt(mvtStk.getDateMvt())
                    .prixUnitaire(mvtStk.getPrixUnitaire())
                    .typeMvt(mvtStk.getTypeMvt())
                    .idEntreprise(mvtStk.getIdEntreprise())
                    .build();
        }
    }
    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        if (mvtStkDto == null) {
            return null;
        } else {
            MvtStk mvtStk = new MvtStk();
            mvtStk.setDateMvt(mvtStkDto.getDateMvt());
            mvtStk.setPrixUnitaire(mvtStkDto.getPrixUnitaire());
            mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
            return mvtStk;
        }
    }
}
