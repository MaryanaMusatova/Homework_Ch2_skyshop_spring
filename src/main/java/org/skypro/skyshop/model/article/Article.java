package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String titleArticle;
    private final String textArticle;

    public Article(String titleArticle, String textArticle, UUID uuid) {
        this.id = UUID.randomUUID();
        this.titleArticle = Objects.requireNonNull(titleArticle, "Title cannot be null");
        this.textArticle = textArticle;
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "Название статьи = " + titleArticle + " , Текст статьи = " + textArticle;
    }

    @Override
    @JsonIgnore
    public String searchTerm() {
        return titleArticle + " , " + textArticle;
    }

    @Override
    @JsonIgnore
    public String searchTipContent() {
        return "ARTICLE";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id.equals(article.id) &&
                titleArticle.equals(article.titleArticle) &&
                textArticle.equals(article.textArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleArticle, textArticle);
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
