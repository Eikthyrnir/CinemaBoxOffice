package com.timbar.uam.wmi.oop.boxoffice.domain;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.TicketRepo;
import org.springframework.stereotype.Component;

@Component
public class TicketCatalog {

    private TicketRepo ticketRepo;

    public TicketCatalog(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket getTicket(int id) {
        return ticketRepo.findById(id).orElseThrow(TicketInfoUnavailableException::new);
    }

}
