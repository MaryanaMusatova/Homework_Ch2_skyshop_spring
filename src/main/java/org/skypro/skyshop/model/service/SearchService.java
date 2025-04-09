package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = Objects.requireNonNull(storageService, "StorageService cannot be null");
    }

    public Set<SearchResult> search(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return Set.of();
        }

        final String normalizedSearch = normalizeString(searchTerm);

        return storageService.getSearchableCollection().stream()
                .filter(item ->
                        normalizeString(item.searchTerm()).contains(normalizedSearch) ||
                                normalizeString(item.searchTipContent()).contains(normalizedSearch)
                )
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static String normalizeString(String input) {
        return input == null ? "" : input.toLowerCase();
    }
}