package com.timbar.uam.wmi.oop.boxoffice.database.repo;

import com.timbar.uam.wmi.oop.boxoffice.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie, Integer> {
}
