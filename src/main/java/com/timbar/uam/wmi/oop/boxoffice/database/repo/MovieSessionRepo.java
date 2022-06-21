package com.timbar.uam.wmi.oop.boxoffice.database.repo;

import com.timbar.uam.wmi.oop.boxoffice.domain.MovieSession;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionRepo extends CrudRepository<MovieSession, Integer> {

    List<MovieSession> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

}
