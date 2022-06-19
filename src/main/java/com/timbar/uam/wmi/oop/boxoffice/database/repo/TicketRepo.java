package com.timbar.uam.wmi.oop.boxoffice.database.repo;

import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<Ticket, Integer> {
}
