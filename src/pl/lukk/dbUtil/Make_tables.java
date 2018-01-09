package pl.lukk.dbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Make_tables
{
    static public String queryCreateOrder    = "CREATE TABLE `order`(id int AUTO_INCREMENT, takeDate varchar(255), startDate varchar(255), problemDesc text, repairDesc text, status varchar(245), price DOUBLE(10,2), partsPrice DOUBLE(10,2), hoursPrice DOUBLE(10,2), hours int, employee_id int, vehicle_id int, customer_id int, PRIMARY KEY (id), FOREIGN KEY (employee_id) REFERENCES employee(id), FOREIGN KEY (vehicle_id) REFERENCES vehicle(id), FOREIGN KEY (customer_id) REFERENCES customer(id) );";
    static public String queryCreateVehicle  = "CREATE TABLE vehicle (id int AUTO_INCREMENT, brand varchar(255), model varchar(255), nextCheckDate varchar(255), productionYear int, serialNum int, customer_id int, PRIMARY KEY(id), FOREIGN KEY (customer_id) REFERENCES customer(id) );";
    static public String queryCreateEmployee = "CREATE TABLE employee(id int AUTO_INCREMENT, name varchar(255), surname varchar(255), adress varchar(255), tel int, note text, hourPrice DOUBLE(10,2), PRIMARY KEY (id) );";
    static public String queryCreateCustomer = "CREATE TABLE customer(id int AUTO_INCREMENT, name varchar(255), surname varchar(255), PRIMARY KEY (id) );";

    static public void mkTables(String query, Connection conn) throws SQLException
    {
        Statement stmt = conn.createStatement();
        int res = stmt.executeUpdate(query);
        System.out.println(res);
    }
}
