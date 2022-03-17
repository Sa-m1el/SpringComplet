package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.CommandeClient;
import org.jeansamuel.swagg.model.LigneCommandeClient;


import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDto {

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private Integer idEntreprise;

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){

        if (commandeClient == null){
            return null;
        }else{
            return CommandeClientDto.builder()
                    .idEntreprise(commandeClient.getIdEntreprise())
                    .code(commandeClient.getCode())
                    .dateCommande(commandeClient.getDateCommande())
                    .client(ClientDto.fromEntity(commandeClient.getClient()))
                    .build();
        }
    }
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){
        if (commandeClientDto == null){
            return null;
        }else{
            CommandeClient commandeClient = new CommandeClient();

            commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
            commandeClient.setCode(commandeClientDto.getCode());
            commandeClient.setDateCommande(commandeClientDto.getDateCommande());
            commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
            return commandeClient;
        }
    }

}
