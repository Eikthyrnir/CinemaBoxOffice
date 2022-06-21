package com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment;

import java.math.BigDecimal;

public class CreditPayment extends Payment {

    private final CreditCard card;

    public CreditPayment(String creditCardNumber, BigDecimal amount) {
        super(amount);
        card = new CreditCard(creditCardNumber);
    }

    @Override
    public void submit() {
        System.out.println("Payment by credit card (" + card.getNumber() + ") submitted");
    }
}
