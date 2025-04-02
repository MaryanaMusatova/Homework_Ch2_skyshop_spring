package org.skypro.skyshop.model.search;

import java.util.Objects;

public class SearchResult {

    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.contentType = Objects.requireNonNull(contentType);
    }

    public static SearchResult fromSearchable(Searchable object) {
        Objects.requireNonNull(object, "Searchable object cannot be null");
        return new SearchResult(
                object.getId().toString(),
                object.searchTerm(),
                object.searchTipContent()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}