package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")  // Добавлен базовый путь

public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = Objects.requireNonNull(storageService, "StorageService must not be null");
        this.searchService = Objects.requireNonNull(searchService, "SearchService must not be null");
    }

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts() {
        return Collections.unmodifiableMap(storageService.getProducts());
    }

    @GetMapping("/articles")
    public Map<UUID, Article> getAllArticles() {
        return Collections.unmodifiableMap(storageService.getArticles());
    }

    @GetMapping("/search")
    public Set<SearchResult> search(@RequestParam String pattern) {
        return Set.copyOf(searchService.search(pattern));
    }
}