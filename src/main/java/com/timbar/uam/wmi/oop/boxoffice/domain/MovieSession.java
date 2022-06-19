package com.timbar.uam.wmi.oop.boxoffice.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class MovieSession {

    private Movie movie;
    private ViewingRoom viewingRoom;
    private LocalDateTime startTime;

}
