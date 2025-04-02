package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        addProduct(new SimpleProduct("ноутбук", 67000, UUID.randomUUID()));
        addProduct(new DiscountedProduct("планшет", 36000, 10, UUID.randomUUID()));
        addProduct(new FixPriceProduct("моноблок", 250000, UUID.randomUUID()));
        addProduct(new SimpleProduct("смартфон", 27400, UUID.randomUUID()));
        addProduct(new DiscountedProduct("монитор", 78000, 15, UUID.randomUUID()));
        addArticle(new Article("статья, ноутбук или компьютер?", "Муки выбора: настольный персональный компьютер или ноутбук — знакомы каждому, кто решил обновить свой технический гардероб полезным устройством", UUID.randomUUID()));
        addArticle(new Article("статья, как выбрать монитор", "При выборе монитора стоит учитывать несколько параметров....", UUID.randomUUID()));
        addArticle(new Article("статья о моноблоках", "медленно работает моноблок, что делать", UUID.randomUUID()));
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void addArticle(Article article) {
        articles.put(article.getId(), article);
    }

    public Set<Searchable> getSearchableCollection() {
        return Stream.concat(
                products.values().stream(),
                articles.values().stream()
        ).collect(Collectors.toSet());
    }

}