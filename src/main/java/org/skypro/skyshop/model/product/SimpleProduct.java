package org.skypro.skyshop.model.product;


import java.util.UUID;

public class SimpleProduct extends Product {

    private final int priceSimpleProduct;

    public SimpleProduct(String title, int priceSimpleProduct, UUID id) {
        super(title, id);

        if (priceSimpleProduct > 1) {
            this.priceSimpleProduct = priceSimpleProduct;
        } else {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0");


        }
    }

    @Override
    public int getPrice() {
        return priceSimpleProduct;
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getTitle() + ": " + getPrice();
    }


}
