package com.codecool.shop.model;

public class User {
    private final String username;
    private final String email;
    private final int phoneNumber;
    private final String billingAddress;
    private final String shippingAddress;
    private int id;

    public User(String username, String email, int phoneNumber, String billingAddress, String shippingAddress) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}
