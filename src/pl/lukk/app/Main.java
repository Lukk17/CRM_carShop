package pl.lukk.app;

import java.sql.Connection;
import java.sql.SQLException;

import pl.lukk.dbUtil.SQL_connection;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.Make_tables;

public class Main {

	public static void main(String[] args) 
	{
	    Make_database.mkDB(Make_database.query);
	    
		try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))	
		{
			Make_tables.mkTables(Make_tables.queryCreateCustomer, conn);
			Make_tables.mkTables(Make_tables.queryCreateEmployee, conn);
			Make_tables.mkTables(Make_tables.queryCreateVehicle, conn);
			Make_tables.mkTables(Make_tables.queryCreateOrder, conn);
		}
		
		catch (SQLException e)
		{
			e.getMessage();
		}
	}
}
