package com.timbar.uam.wmi.oop.boxoffice.domain.pos;

import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketCatalog {

    private Map<Integer, Ticket> tickets = new HashMap<>();

    public TicketCatalog() {
        //test data
        int id1 = 100;
        int id2 = 200;
//        tickets.put(id1, new Ticket(id1, ))
    }

    public Ticket getTicket(int id) {
        return tickets.get(id);
    }
}
