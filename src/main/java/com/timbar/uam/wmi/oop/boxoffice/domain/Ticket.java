package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Ticket {

    private int id;
    private MovieSession movieSession;
    private ViewingRoomSeat viewingRoomSeat;
    private SeatState state;
    private BigDecimal price;

}
