package org.skypro.skyshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    // Тест 1: Поиск при отсутствии объектов в хранилище
    @Test
    public void search_WhenStorageEmpty_ReturnsEmptySet() {
        when(storageService.getSearchableCollection()).thenReturn(Collections.emptySet());

        Set<SearchResult> result = searchService.search("ноутбук");

        assertTrue(result.isEmpty());
        verify(storageService).getSearchableCollection();
    }

    // Тест 2: Поиск когда объекты есть, но нет подходящих
    @Test
    public void search_WhenNoMatchingItems_ReturnsEmptySet() {
        SimpleProduct product = new SimpleProduct("мышь", 1000, UUID.randomUUID());
        Article article = new Article("о клавиатурах", "статья про клавиатуры", UUID.randomUUID());

        when(storageService.getSearchableCollection()).thenReturn(Set.of(product, article));

        Set<SearchResult> result = searchService.search("монитор");

        assertTrue(result.isEmpty());
        verify(storageService).getSearchableCollection();
    }

    // Тест 3: Поиск когда есть подходящий объект (продукт)
    @Test
    public void search_WhenMatchingProductExists_ReturnsResult() {
        SimpleProduct product = new SimpleProduct("монитор", 15000, UUID.randomUUID());
        when(storageService.getSearchableCollection()).thenReturn(Set.of(product));

        Set<SearchResult> result = searchService.search("монитор");

        assertEquals(1, result.size());
        verify(storageService).getSearchableCollection();
    }

    // Тест 4: Поиск когда есть подходящий объект (статья)
    @Test
    public void search_WhenMatchingArticleExists_ReturnsResult() {
        Article article = new Article("о мониторах", "статья про мониторы", UUID.randomUUID());
        when(storageService.getSearchableCollection()).thenReturn(Set.of(article));

        Set<SearchResult> result = searchService.search("монитор");

        assertEquals(1, result.size());
        verify(storageService).getSearchableCollection();
    }

    // Дополнительный тест: проверка регистронезависимости
    @Test
    public void search_IsCaseInsensitive_ReturnsResult() {
        Article article = new Article("О МОНИТОРАХ", "СТАТЬЯ ПРО МОНИТОРЫ", UUID.randomUUID());
        when(storageService.getSearchableCollection()).thenReturn(Set.of(article));

        Set<SearchResult> result = searchService.search("мони");

        assertEquals(1, result.size());
        verify(storageService).getSearchableCollection();
    }
}