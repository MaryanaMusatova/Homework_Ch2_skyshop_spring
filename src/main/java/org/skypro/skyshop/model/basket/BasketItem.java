package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public final class BasketItem {

    private final Product product;

    private final Integer quantity;


    public BasketItem(Product product, Integer count) {
        this.product = product;
        this.quantity = count;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

}