package com.timbar.uam.wmi.oop;

import com.timbar.uam.wmi.oop.boxoffice.database.DataSourceManager;
import com.timbar.uam.wmi.oop.boxoffice.database.repo.*;
import com.timbar.uam.wmi.oop.boxoffice.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

@SpringBootApplication
@Component
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private MovieSessionRepo movieSessionRepo;
    @Autowired
    private ViewingRoomSeatRepo viewingRoomSeatRepo;
    @Autowired
    private ViewingRoomRepo viewingRoomRepo;
    @Autowired
    private TicketRepo ticketRepo;

    @PostConstruct
    private void testcrud() {

        log.info("hello world");
        log.warn("hello world");

        try {
            Movie movie = movieRepo.findById(2).get();
            log.debug(movie.toString());
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}