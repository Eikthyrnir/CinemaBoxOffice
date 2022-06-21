package com.timbar.uam.wmi.oop.boxoffice.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionAdapter {

    MovieSession getById(int id);

    List<MovieSession> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

}
