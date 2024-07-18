package vn.edu.likelion.connectDB.utils;

import java.sql.*;

public class MyConnect {
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "system";
    private String pass = "123456";
    private Connection conn = null;
    public Connection openConnect() throws SQLException {
        conn = DriverManager.getConnection(url,user,pass);

        return conn;
    }

    public void closeConnect() {
        if (conn != null) {

        }
    }


}
