package com.codecool.shop.model;

public class Order extends BaseModel{
    private final Cart cart;
    private final User user;
    private OrderStatus orderStatus;

    public Order(Cart cart, User user) {
        super("Order");
        this.cart = cart;
        this.user = user;
        this.orderStatus = OrderStatus.CHECKED;
    }

    public Cart getCart() {
        return cart;
    }

    public User getUser() {
        return user;
    }

    public void pay() {
        orderStatus = OrderStatus.PAYED;
    }

    public void confirm() {
        orderStatus = OrderStatus.CONFIRMED;
    }

    public void ship() { orderStatus = OrderStatus.SHIPPED; }

    public String getName() {
        return "Order-" + id;
    }
}
