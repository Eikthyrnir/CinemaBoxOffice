package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ticket", schema = "cinema_box_office")
public class Ticket {

    @Id
    private int id;
    @ManyToOne
    private MovieSession movieSession;
    @ManyToOne
    private ViewingRoomSeat viewingRoomSeat;
    private SeatState state;
    private BigDecimal price;

}
