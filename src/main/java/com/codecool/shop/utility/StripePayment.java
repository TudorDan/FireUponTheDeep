package com.codecool.shop.utility;

import com.codecool.shop.model.CreditCard;

public class StripePayment {
    private final CreditCard creditCard;
    private boolean success;

    public StripePayment(CreditCard creditCard) {
        this.creditCard = creditCard;
        success = false;
    }

    public boolean executePayment() {
        success = true;
        return success;
    }
}
