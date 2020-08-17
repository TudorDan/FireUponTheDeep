package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
    private final List<Item> items = new ArrayList<>();

    public void addProduct(Product product, Price price) {
        //search for product
        boolean found = false;
        for (Item item : items) {
            //if found increase quantity
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
            }
        }
        //if not found add new item
        if (!found) {
            items.add(new Item(product, price));
        }
    }

    public void removeProduct(Product product) {
        //search for product
        for (Item item : items) {
            //if found decrease quantity
            if (item.getProduct().getId() == product.getId()) {
                if(item.getQuantity()>1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    items.remove(item);
                }
                break;
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public int getNumberOfItems() {
        int nrItems = 0;
        for (Item item : items) {
            nrItems += item.getQuantity();
        }
        return nrItems;
    }

    public Price getTotalPrice() {
        float sum = 0;
        for (Item item : items) {
            sum += item.getTotalPrice().getSum();
        }
        return new Price(sum, "USD", new Date());
    }

    public int getQuantityOfProduct(Product product) {
        //search for product
        for (Item item : items) {
            //if found return quantity
            if (item.getProduct().getId() == product.getId()) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    public void removeAllProducts() {
        items.clear();
    }
}
