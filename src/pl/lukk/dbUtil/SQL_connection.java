package pl.lukk.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_connection
{
    static public Connection sqlConnect(String connDbName) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + connDbName + "?useSSL=false", "root", "coderslab");
        return conn;
    }
}