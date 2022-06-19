package com.timbar.uam.wmi.oop.boxoffice.database;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceManager {

    private static final Logger log = LoggerFactory.getLogger(DataSourceManager.class);

    private static ComboPooledDataSource dataSource = null;

    private static final String properties_path = "/database.properties";
    //настройки приложения из файла настроек, содержащие значения полей 'db.driver', 'db.url', 'db.user', 'db.password'.
    private static Properties db_properties;

    static {
        loadProperties();
    }

    public static DataSource getDataSource() throws Exception {
        if (dataSource == null) {
            setupDataSource();
        }
        return dataSource;
    }

    private static void loadProperties() {
        try {
            db_properties = new Properties();
            db_properties.load(DataSourceManager.class.getResourceAsStream(properties_path));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void setupDataSource() throws Exception {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(db_properties.getProperty("db.driver"));
            dataSource.setJdbcUrl(db_properties.getProperty("db.url"));
            dataSource.setUser(db_properties.getProperty("db.user"));
            dataSource.setPassword(db_properties.getProperty("db.password"));

            // Optional Settings
            dataSource.setInitialPoolSize(4);
            dataSource.setMinPoolSize(4);
            dataSource.setAcquireIncrement(2);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxStatements(50);
            dataSource.setCheckoutTimeout(2000);
            dataSource.setPreferredTestQuery("select 1");
            dataSource.setTestConnectionOnCheckout(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}

