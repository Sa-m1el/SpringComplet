package org.jeansamuel.swagg.dto;

import lombok.Builder;
import lombok.Data;

import org.jeansamuel.swagg.model.Ventes;


import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVente;

    public static VentesDto fromEntity(Ventes ventes) {

        if (ventes == null) {
            return null;
        } else {
            return VentesDto.builder()
                    .code(ventes.getCode())
                    .dateVente(ventes.getDateVente())
                    .commentaire(ventes.getCommentaire())
                    .idEntreprise(ventes.getIdEntreprise())
                    .build();
        }
    }

    public static Ventes toEntity(VentesDto ventesDto) {
        if (ventesDto == null) {
            return null;
        } else {
            Ventes ventes = new Ventes();
            ventes.setCode(ventesDto.getCode());
            ventes.setDateVente(ventesDto.getDateVente());
            ventes.setIdEntreprise(ventesDto.getIdEntreprise());
            ventes.setCommentaire(ventesDto.getCommentaire());

            return ventes;
        }
    }
}
