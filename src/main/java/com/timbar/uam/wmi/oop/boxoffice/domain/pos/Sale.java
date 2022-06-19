package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private Payment payment;
    private List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public BigDecimal getTotal() {
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void makePayment(BigDecimal cashTendered) {
        payment = new Payment(cashTendered);
    }

    public BigDecimal getBalance() {
        return payment.getAmount().subtract(getTotal());
    }

}
