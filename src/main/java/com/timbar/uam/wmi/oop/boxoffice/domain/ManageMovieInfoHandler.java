package com.timbar.uam.wmi.oop.boxoffice.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ManageMovieInfoHandler {

    private MovieSessionCatalog movieSessionCatalog;

    public List<MovieSession> getMovieSessions(LocalDateTime from, LocalDateTime to) {
        return movieSessionCatalog.getSessionsInPeriod(from, to);
    }

    public List<MovieSession> getMovieSessions(LocalDateTime from, LocalDateTime to,
                                               String keyword) {
        return movieSessionCatalog.getSessionsInPeriod(from, to)
                .stream()
                .filter(session -> session.getMovie().getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
