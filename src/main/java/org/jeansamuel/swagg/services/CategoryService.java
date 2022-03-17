package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto) throws InvalidEntityException;

    CategoryDto findById(Integer idCategory) throws EntityNotFoundException;

    CategoryDto findByCode(String codeCategory) throws EntityNotFoundException;

    List<CategoryDto> findAll();

    void delete(Integer idCategory);
}
