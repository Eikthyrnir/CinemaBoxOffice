package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movie_session", schema = "cinema_box_office")
public class MovieSession {

    @Id
    private int id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private ViewingRoom viewingRoom;
    private LocalDateTime startTime;

}
