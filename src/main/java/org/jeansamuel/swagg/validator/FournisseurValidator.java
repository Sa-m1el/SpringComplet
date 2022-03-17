package org.jeansamuel.swagg.validator;

import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors = new ArrayList<>();

        if(fournisseurDto == null){
            errors.add("Veillez renseigner le nom du fournisseur");
            errors.add("Veillez renseigner le prenom du fournisseur");
            errors.add("Veillez renseigner l'adresse mail du fournisseur");
            errors.add("Veillez renseigner le numero de telephone du fournisseur");
            return errors;

        }

        if(!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Veillez renseigner le nom du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Veillez renseigner le prenom du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getMail())){
            errors.add("Veillez renseigner l'adresse mail du fournisseur");
        }
        if(!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Veillez renseigner le numero de telephone du fournisseur");
        }

        return errors;
    }
}
