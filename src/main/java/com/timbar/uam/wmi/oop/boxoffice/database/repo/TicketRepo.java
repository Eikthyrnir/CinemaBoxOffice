package com.timbar.uam.wmi.oop.boxoffice.database.repo;

import com.timbar.uam.wmi.oop.boxoffice.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepo extends CrudRepository<Ticket, Integer> {

    List<Ticket> findByMovieSessionId(int movieSessionId);

}
