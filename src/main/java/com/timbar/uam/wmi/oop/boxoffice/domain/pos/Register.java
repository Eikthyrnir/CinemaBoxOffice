package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;

import java.math.BigDecimal;

public class Register {

    private TicketCatalog ticketCatalog;
    private Sale sale;


    public Register(TicketCatalog ticketCatalog) {
        this.ticketCatalog = ticketCatalog;
    }

    public void makeNewSale() {
        sale = new Sale();
    }

    public void enterTicket(int ticketId) {
        sale.addTicket(ticketCatalog.getTicket(ticketId));
    }

    public void makePayment(BigDecimal cashTendered) {
        sale.makePayment(cashTendered);
    }

    public Sale getSale() {
        return sale;
    }

}
