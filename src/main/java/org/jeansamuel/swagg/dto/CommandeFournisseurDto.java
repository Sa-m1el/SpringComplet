package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.*;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private Integer idEntreprise;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){

        if (commandeFournisseur == null){
            return null;
        }else{
            return CommandeFournisseurDto.builder()
                    .id(commandeFournisseur.getId())
                    .code(commandeFournisseur.getCode())
                    .dateCommande(commandeFournisseur.getDateCommande())
                    .idEntreprise(commandeFournisseur.getIdEntreprise())
                    .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                    .build();
        }
    }
    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto){
        if (commandeFournisseurDto == null){
            return null;
        }else{
            CommandeFournisseur commandeFournisseur = new CommandeFournisseur();

            commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
            commandeFournisseur.setCode(commandeFournisseurDto.getCode());
            commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
            commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));
            return commandeFournisseur;
        }
    }

}
