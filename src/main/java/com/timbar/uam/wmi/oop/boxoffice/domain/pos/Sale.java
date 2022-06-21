package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.TicketRepo;
import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;
import com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment.CashPayment;
import com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment.CreditCard;
import com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment.CreditPayment;
import com.timbar.uam.wmi.oop.boxoffice.domain.pos.payment.Payment;
import com.timbar.uam.wmi.oop.boxoffice.domain.SeatState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private final TicketRepo ticketRepo;

    private Payment payment;
    private final List<Ticket> tickets = new ArrayList<>();

    public Sale(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public BigDecimal getTotal() {
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void makeCashPayment(BigDecimal cashTendered) {
        payment = new CashPayment(cashTendered);
        submitSale();
    }

    public void makeCreditPayment(String creditCardNumber) {
        payment = new CreditPayment(creditCardNumber, getTotal());
        submitSale();
    }

    public void submitSale() {
        payment.submit();
        for (Ticket ticket : tickets) {
            ticket.setState(SeatState.OCCUPIED);
        }
        ticketRepo.saveAll(tickets);
    }

    public BigDecimal getBalance() {
        return payment.getAmount().subtract(getTotal());
    }

}
