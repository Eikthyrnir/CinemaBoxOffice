package com.timbar.uam.wmi.oop;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.*;
import com.timbar.uam.wmi.oop.boxoffice.domain.*;
import com.timbar.uam.wmi.oop.boxoffice.domain.pos.Register;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Component
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    private MovieSessionAdapter movieSessionAdapter;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private Register register;

    private static final String help =
            """
                    Hello, this is console driven cinema box office application.
                    You can use the following commands:
                                
                    help - prints this message
                                
                    moviesessions - prints today sessions information
                    moviesession <session_id> - prints session description and places
                                
                    makeNewSale - starts new sale
                    enterTicket <ticket_id> - add specified ticket to sale
                    makeCashPayment <cash amount> - performs payment
                    makeCreditPayment - performs payment by credit card
                                
                    exit - exit the application
                    """;

    private void prettyPrintTicket(Ticket ticket) {
        ViewingRoomSeat seat = ticket.getViewingRoomSeat();
        System.out.println("Ticket " + ticket.getId() + " (id)"
                + " Room: " + seat.getRoom().getName()
                + "\n             "
                + "Row " + seat.getRow() + " place " + seat.getColumn()
                + "\n             "
                + "Price: " + ticket.getPrice() + " PLN"
                );
        if (ticket.getState().equals(SeatState.FREE)) {
            System.out.println("              FREE");
        } else {
            System.out.println("              ALREADY TAKEN");
        }
    }

    private void prettyPrintMovieSession(MovieSession session) {
        System.out.println("Session " + session.getId() + " (id)"
                + " Film: " + session.getMovie().getTitle()
                + "\n             "//align output
                + "Start time: " + session.getStartTime()
                + " Room: " + session.getViewingRoom().getName()
        );
    }

    @PostConstruct
    private void runSimpleConsoleUI() {

        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        cmd_cycle:
        while (true) {
            try {
                String cmd = scanner.nextLine();
                String[] cmdParts = cmd.split("\\s+");
                switch (cmdParts[0]) {
                    case "exit" -> {
                        System.out.println("Good buy");
                        break cmd_cycle;
                    }
                    case "help" -> {
                        System.out.println(help);
                    }
                    case "moviesessions" -> {
                        movieSessionAdapter.findByStartTimeBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(10))
                                .forEach(this::prettyPrintMovieSession);
                    }
                    case "moviesession" -> {
                        try {
                            MovieSession movieSession = movieSessionAdapter.getById(Integer.parseInt(cmdParts[1]));
                            prettyPrintMovieSession(movieSession);
                            List<Ticket> tickets = ticketRepo.findByMovieSessionId(movieSession.getId());
                            tickets.forEach(this::prettyPrintTicket);
                        } catch (MovieSessionInfoUnavailableException e) {
                            System.out.println("No session with such ID :(");
                        }
                    }
                    case "makeNewSale" -> register.makeNewSale();
                    case "enterTicket" -> {
                        String ticketId = "'not entered'";
                        try {
                            ticketId = cmdParts[1];
                            register.enterTicket(Integer.parseInt(ticketId));
                            System.out.println("total price: " + register.getSale().getTotal());
                        } catch (Exception e) {
                            System.out.println("cant find ticket with such id = " + ticketId);
                        }
                    }
                    case "makeCashPayment" -> {
                        System.out.println("Please enter cash amount");
                        BigDecimal amount = new BigDecimal(scanner.nextLine());
                        register.makeCashPayment(amount);
                        System.out.println("balance: " + register.getSale().getBalance());
                    }
                    case "makeCreditPayment" -> {
                        System.out.println("Please enter credit card");
                        register.makeCreditPayment(scanner.nextLine());
                        System.out.println("balance: " + register.getSale().getBalance());
                    }
                    default -> {
                        System.out.println("Unknown command :(");
                        System.out.println(help);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}