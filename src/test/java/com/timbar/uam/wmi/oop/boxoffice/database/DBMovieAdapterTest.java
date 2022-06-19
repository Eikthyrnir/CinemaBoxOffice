package com.timbar.uam.wmi.oop.boxoffice.database;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.MovieRepo;
import com.timbar.uam.wmi.oop.boxoffice.domain.Movie;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class DBMovieAdapterTest {

    private static final Logger log = LoggerFactory.getLogger(DBMovieAdapterTest.class);

    @Test
    void getMovieById() {
        try {
            int id1 = 2;
            DBMovieAdapter movieAdapter = new DBMovieAdapter();
            Movie movie1 = movieAdapter.getMovieById(id1);
            log.debug(movie1.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }



}