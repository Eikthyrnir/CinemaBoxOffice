package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "viewing_room_seat", schema = "cinema_box_office")
public class ViewingRoomSeat {

    @Id
    private int id;
    private int row;
    private int column;
    @ManyToOne(targetEntity = SeatType.class)
    private SeatType type;
    @ManyToOne(targetEntity = ViewingRoom.class)
    @JoinColumn(name = "viewing_room_id")
    ViewingRoom room;

}
