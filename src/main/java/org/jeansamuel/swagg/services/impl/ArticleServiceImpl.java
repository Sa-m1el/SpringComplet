package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.ArticleRepository;
import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.services.ArticleService;
import org.jeansamuel.swagg.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;

    }
    @Override
    public ArticleDto save(ArticleDto articleDto) throws InvalidEntityException {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", articleDto);
            throw new InvalidEntityException("L'artice n'est pas valide", ErrorCodes.ARTICLE_NOT_FOUND, errors);
        }
        //return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
        Article savedArticle = articleRepository.save(ArticleDto.toEntity(articleDto));
        return ArticleDto.fromEntity(savedArticle);
    }

    @Override
    public ArticleDto findById(Integer id) throws EntityNotFoundException {
        if ( id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);
      //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucun article avec l'ID =" + id + " n'a été trouvé dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) throws EntityNotFoundException {
        if ( !StringUtils.hasLength(codeArticle)){
            log.error("Article CODE is null");
            return null;
        }

        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucun article avec le CODE = " + codeArticle + " n'a été trouvé dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if ( id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
