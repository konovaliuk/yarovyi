package com.servletproject.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionPool {

    private static final BasicDataSource ds = new BasicDataSource();
    static {
        ResourceBundle resources = ResourceBundle.getBundle("ConnBundle");
        ds.setDriverClassName(resources.getString("driver"));
        ds.setUrl(resources.getString("url"));
        ds.setUsername(resources.getString("login"));
        ds.setPassword(resources.getString("password"));
    }
    public ConnectionPool(){

    }

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }


}
