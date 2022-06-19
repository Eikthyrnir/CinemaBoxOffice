package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "movie", schema = "cinema_box_office")
public class Movie {

    @Id
    private int id;
    private String title;
    private String description;
    @Column(name = "duration_mins")
    private int durationInMins;
    //m.b. later
//    private List<String> genres;
//    private LocalDateTime releaseDate;
//    private String country;
//      and other information

}
