package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.CategoryRepository;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Category;
import org.jeansamuel.swagg.services.CategoryService;
import org.jeansamuel.swagg.validator.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) throws InvalidEntityException {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", categoryDto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_FOUND, errors);
        }
        //return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
       Category savedCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));
        return CategoryDto.fromEntity(savedCategory );

    }

    @Override
    public CategoryDto findById(Integer idCategory) throws EntityNotFoundException {
        if ( idCategory == null){
            log.error("Category ID is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(idCategory);
        //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucune categorie avec l'ID =" + idCategory + " n'a été trouvé dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String codeCategory) throws EntityNotFoundException {
        if ( !StringUtils.hasLength(codeCategory)){
            log.error("Category CODE is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findArticleByCode(codeCategory);
        //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucune categorie avec le CODE = " + codeCategory + " n'a été trouvé dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer idCategory) {

        if ( idCategory == null){
            log.error("Article ID is null");
            return;
        }
        categoryRepository.deleteById(idCategory);

    }

}
