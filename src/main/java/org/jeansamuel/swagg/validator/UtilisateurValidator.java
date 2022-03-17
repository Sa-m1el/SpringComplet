package org.jeansamuel.swagg.validator;


import org.jeansamuel.swagg.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veillez renseigner le nom d'utilisateur");
            errors.add("Veillez renseigner le prenom d'utilisateur");
            errors.add("Veillez renseigner 'l'email' d'utilisateur");
            errors.add("Veillez renseigner le mot de passe d'utilisateur");
            errors.add("Veillez renseigner l'adresse");
            return errors;
        }

        if(!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veillez renseigner le nom d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veillez renseigner le prenom d'utilisateur");
        }
        if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("Veillez renseigner le mot de passe d'utilisateur");
        }
        if(!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veillez renseigner 'le mail' d'utilisateur");
        }
        if(utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veillez renseigner 'la date de naissance' d'utilisateur");
        }
        if(utilisateurDto.getAdresse() == null){
            errors.add("Veillez renseigner l'adresse");
        }else{
            if(!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("Le champ 'Adresse 1' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("Le champ 'ville' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("Le  'code postale' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("Le champ 'pays' est obligatoire");
            }
        }

        return errors;

    }
}
