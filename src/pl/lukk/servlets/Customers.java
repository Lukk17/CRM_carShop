package pl.lukk.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.lukk.dao.Customer;

import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet("/customers")
public class Customers extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {

            Customer[] customers = Customer.loadAll(conn);

            request.setAttribute("customers", customers);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            List<Customer> customers = Arrays.asList(Customer.loadAll(conn));                                                                                                     
            request.setAttribute("customers", customers);

            //              add customer
            String[] name = request.getParameterValues("name");
            String[] surname = request.getParameterValues("surname");

            for (int i = 0; i < name.length; i++)
            {
                Customer temp = new Customer(name[i], surname[i]);
                customers.add(temp);
                temp.saveToDB(conn);

            }
            //              edit customer

            String[] editId = request.getParameterValues("editId");

            for (int i = 0; i < editId.length; i++)
            {

                Customer tmpCustomer = new Customer(Integer.parseInt(editId[i]));                                                                                                   
                tmpCustomer.setName(request.getParameterValues("editName")[i]);                                                                                                    
                tmpCustomer.setSurname(request.getParameterValues("editSurname")[i]);

                tmpCustomer.saveToDB(conn);

            }
            //              delete customer

            String[] deleteId = request.getParameterValues("deleteId");

            for (int i = 0; i < deleteId.length; i++)
            {
                Customer tmpCustomer = new Customer(Integer.parseInt(deleteId[i]));
                tmpCustomer.DeleteCustomer(conn);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
    }
}
