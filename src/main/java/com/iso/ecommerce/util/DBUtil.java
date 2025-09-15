package com.iso.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private DBUtil() {
    }

    private static String url = "jdbc:postgresql://localhost:5432/ecommerce";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }


}
