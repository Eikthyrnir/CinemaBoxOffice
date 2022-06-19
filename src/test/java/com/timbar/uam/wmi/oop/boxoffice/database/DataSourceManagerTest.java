package com.timbar.uam.wmi.oop.boxoffice.database;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceManagerTest {

    private static final Logger log = LoggerFactory.getLogger(DataSourceManagerTest.class);

    @Test
    void getDataSource() {
        try {
            Connection connection = DataSourceManager.getDataSource().getConnection();
            ResultSet rs = connection
                    .prepareStatement("select 100")
                    .executeQuery();
            while(rs.next()) {
                assert(rs.getString(1).equals("100"));
            }
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }
}