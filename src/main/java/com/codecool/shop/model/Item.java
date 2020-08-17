package com.codecool.shop.model;

import java.util.Date;

public class Item {
    private final Product product;
    private final Price price;
    private int quantity;

    public Item(Product product, Price price) {
        this.product = product;
        this.price = price;
        quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }

    public Price getPrice() { return price; }

    public Price getTotalPrice() {
        return new Price(price.getSum() * quantity, "USD", new Date());
    }
}
