package org.jeansamuel.swagg.controller;

import org.jeansamuel.swagg.controller.api.CategoryApi;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) throws InvalidEntityException {

        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer idCategory) throws EntityNotFoundException {
        return categoryService.findById(idCategory);
    }

    @Override
    public CategoryDto findByCode(String code) throws EntityNotFoundException {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer idCategory) {
        categoryService.delete(idCategory);
    }
}
