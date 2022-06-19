package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "seat_type", schema = "cinema_box_office")
public class SeatType {

    @Id
    int id;
    String name;

}