package pl.lukk.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Make_database
{
    static public String newDbName = "CRM_carShop";
    static public String query     = "CREATE DATABASE " + newDbName + " character set utf8 collate utf8_unicode_ci ";

    static public void mkDB(String query)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root",
                "coderslab"))
        {
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate(query);
            System.out.println(res);
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
    }
}
