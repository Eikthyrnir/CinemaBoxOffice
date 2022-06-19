package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "viewing_room", schema = "cinema_box_office")
public class ViewingRoom {

    @Id
    private int id;
    private String name;

    //private String description;necessary?

    //Is it really necessary to have places list here? for what?
//    @OneToMany(targetEntity = ViewingRoomSeat.class)
//    private List<ViewingRoomSeat> places;
//    private int numPlaces;//necessary? only if lazy initialize places list

}
