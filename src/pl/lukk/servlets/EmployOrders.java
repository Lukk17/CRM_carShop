package pl.lukk.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.lukk.dao.Order;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet("/employOrders")
public class EmployOrders extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            Order[] temp = Order.loadByEmployee(conn, id);

            request.setAttribute("orders", temp);
            request.setAttribute("employee_id", id);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/employOrders.jsp").forward(request, response);
    }
}
