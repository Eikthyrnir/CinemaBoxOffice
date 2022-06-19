package com.timbar.uam.wmi.oop.boxoffice.database.repo;

import com.timbar.uam.wmi.oop.boxoffice.domain.MovieSession;
import org.springframework.data.repository.CrudRepository;

public interface MovieSessionRepo extends CrudRepository<MovieSession, Integer> {
}
