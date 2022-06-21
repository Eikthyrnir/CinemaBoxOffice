package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.TicketRepo;
import com.timbar.uam.wmi.oop.boxoffice.domain.TicketInfoUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Register {

    private final TicketRepo ticketRepo;
    private Sale sale;

    @Autowired
    public Register(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public void makeNewSale() {
        sale = new Sale(ticketRepo);
    }

    public void enterTicket(int ticketId) {
        sale.addTicket(ticketRepo.findById(ticketId)
                .orElseThrow(TicketInfoUnavailableException::new));
    }

    public void makeCashPayment(BigDecimal cashTendered) {
        sale.makeCashPayment(cashTendered);
    }

    public void makeCreditPayment(String creditCardNumber) {
        sale.makeCreditPayment(creditCardNumber);
    }

    public Sale getSale() {
        return sale;
    }

}
