package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Value;

@Value
public class Movie {

    private String title;
    private String description;
    private int durationInMins;
    //m.b. later
//    private List<String> genres;
//    private LocalDateTime releaseDate;
//    private String country;
//      and other information

}
