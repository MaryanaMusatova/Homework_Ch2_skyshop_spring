package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    static int FIX_PRICE = 250000;

    public FixPriceProduct(String title, int FIX_PRICE, UUID id) {
        super(title, id);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getTitle() + ": фиксированная цена: " + getPrice();
    }


}
