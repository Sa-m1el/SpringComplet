package org.jeansamuel.swagg.controller;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;
import org.jeansamuel.swagg.controller.api.ArticleApi;
import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@CrossOrigin("*")
@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) throws InvalidEntityException {
        return articleService.save(articleDto);
    }


    @Override

    public ArticleDto findById(Integer id) throws EntityNotFoundException {
        return articleService.findById(id);
    }


    @Override
    //throws EntityNotFoundException
    public ArticleDto findByCodeArticle(String codeArticle) throws EntityNotFoundException {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
