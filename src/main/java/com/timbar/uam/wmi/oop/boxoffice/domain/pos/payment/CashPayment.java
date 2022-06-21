package com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment;

import java.math.BigDecimal;

public class CashPayment extends Payment {

    public CashPayment(BigDecimal amount) {
        super(amount);
    }

    @Override
    public void submit() {
        System.out.println("CashPayment submitted");
    }
}
