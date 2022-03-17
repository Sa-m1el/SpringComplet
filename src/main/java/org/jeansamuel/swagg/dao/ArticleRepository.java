package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer>{

    //Article findByCodeArticle(String code);
    // Requete personnalisé

   /* @Query("select a from article where codearticle = :code and designation = :designation")
    List<Article> finfByCustomQuery(@Param("code") String c, @Param("designation") String d);*/

    /* @Query(value = "select * from article where code = :code , nativeQuery = true)
    List<Article> finfByCustomQuery(@Param("code") String c, @Param("designation") String d);*/

    //List<Article> findByCodeArticleAndDesignation(String codeArticle, String designation);

    Optional<Article> findArticleByCodeArticle(String codeArticle);
}
