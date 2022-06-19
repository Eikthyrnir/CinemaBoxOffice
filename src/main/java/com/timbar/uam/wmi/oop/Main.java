package com.timbar.uam.wmi.oop;

import com.timbar.uam.wmi.oop.boxoffice.database.DataSourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("hello world");
        log.warn("hello world");
        log.error("hello world");


    }

}