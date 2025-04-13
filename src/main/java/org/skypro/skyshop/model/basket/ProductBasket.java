package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Component
public class ProductBasket {

    private final Map<UUID, Integer> productsBasket;


    public ProductBasket() {
        this.productsBasket = new TreeMap<>();
    }

    public void addProduct(UUID id) {
        Objects.requireNonNull(id, "ID продукта не может быть null");
        if (productsBasket.containsKey(id)) {
            productsBasket.put(id, productsBasket.get(id) + 1);
        } else {
            productsBasket.put(id, 1);
        }
    }

    public Map<UUID, Integer> getAllProductsInBasket() {
        return Collections.unmodifiableMap(productsBasket);
    }
}