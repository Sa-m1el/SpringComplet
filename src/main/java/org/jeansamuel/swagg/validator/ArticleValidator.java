package org.jeansamuel.swagg.validator;

import org.jeansamuel.swagg.dto.ArticleDto;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if(articleDto == null){
            errors.add("Veillez renseigner le code de l'article");
            errors.add("Veillez renseigner le prix unitaire ht de l'article");
            errors.add("Veillez renseigner le prix unitaire Ttc de l'article");
            errors.add("Veillez renseigner le taux tva de l'article");
            errors.add("Veillez renseigner la categorie de l'article");
            return errors;
        }

        if(!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veillez renseigner le code de l'article");
        }

        if(articleDto.getPrixUnitaireHt() == null){
            errors.add("Veillez renseigner le prix unitaire ht de l'article");
        }
        if(articleDto.getPrixUnitaireTtc() == null){
            errors.add("Veillez renseigner le prix unitaire Ttc de l'article");
        }
        if(articleDto.getTauxTVA() == null) {
            errors.add("Veillez renseigner le taux tva de l'article");
        }

        if(articleDto.getCategory() == null) {
            errors.add("Veillez renseigner la categorie de l'article");
        }
        return errors;
    }

}

