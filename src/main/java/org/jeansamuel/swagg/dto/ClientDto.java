package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Adresse;
import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.CommandeClient;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){

        if (client == null){
            return null;
        }else{
            return ClientDto.builder()
                    .idEntreprise(client.getId())
                    .id(client.getId())
                    .nom(client.getNom())
                    .prenom(client.getPrenom())
                    .adresse(client.getAdresse())
                    .photo(client.getPhoto())
                    .mail(client.getMail())
                    .numTel(client.getNumTel())
                    .build();
        }
    }
    public static Client toEntity(ClientDto clientDto){
        if (clientDto == null){
            return null;
        }else{
            Client client = new Client();
            /*client.setIdEntreprise(clientDto.getId());*/
            client.setId(clientDto.getId());
            client.setNom(clientDto.getNom());
            client.setPrenom(clientDto.getPrenom());
            client.setAdresse(clientDto.getAdresse());
            client.setPhoto(clientDto.getPhoto());
            client.setMail(clientDto.getMail());
            client.setNumTel(clientDto.getNumTel());
            return client;
        }
    }

}
