package org.jeansamuel.swagg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.model.Category;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            return null;

        } else {
            return CategoryDto.builder()
                    .id(category.getId())
                    .code(category.getCode())
                    .designation(category.getDesignation())
                    .idEntreprise(category.getIdEntreprise())
                    .build();

        }
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;

        } else {
             Category category = new Category();

             category.setIdEntreprise(categoryDto.getIdEntreprise());
            category.setId(categoryDto.getId());
            category.setCode(categoryDto.getCode());
            category.setDesignation(categoryDto.getDesignation());
                  return category;
        }
    }
}
