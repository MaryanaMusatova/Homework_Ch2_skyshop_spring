package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int total;

    public UserBasket(List<BasketItem> items, int total) {
        this.items = List.copyOf(items);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<BasketItem> getUserBasket() {
        return items;
    }
}