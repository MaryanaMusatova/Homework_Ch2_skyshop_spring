package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {
    private final String title;
    private final UUID id;

    public Product(String title, UUID id) {
        this.id = id;
        if (title != null && !title.isBlank()) {
            this.title = title;
        } else throw new IllegalArgumentException("Неправильное название продукта");

    }

    public String getTitle() {
        return title;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();


    @Override
    @JsonIgnore
    public String searchTerm() {
        return title;
    }

    @Override
    @JsonIgnore
    public String searchTipContent() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return title.equals(product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
