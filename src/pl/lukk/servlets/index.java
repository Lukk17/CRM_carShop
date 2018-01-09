package pl.lukk.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.lukk.dao.Order;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet(name = "Index", urlPatterns =
{ "/index" })
public class index extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {

            Order[] orders = Order.loadAll(conn);

            request.setAttribute("orders", orders);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
