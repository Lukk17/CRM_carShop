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

import pl.lukk.dao.Order;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet("/orders")
public class Orders extends HttpServlet
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

        getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            List<Order> orders = Arrays.asList(Order.loadAll(conn));
            request.setAttribute("orders", orders);

            //              add order
            String[] takeDate = request.getParameterValues("takeDate");
            String[] startDate = request.getParameterValues("startDate");
            String[] employee_id = request.getParameterValues("employee_id");
            String[] problemDesc = request.getParameterValues("problemDesc");
            String[] repairDesc = request.getParameterValues("repairDesc");
            String[] status = request.getParameterValues("status");
            String[] vehicle_id = request.getParameterValues("vehicle_id");
            String[] price = request.getParameterValues("price");
            String[] partsPrice = request.getParameterValues("partsPrice");
            String[] hoursPrice = request.getParameterValues("hoursPrice");
            String[] hours = request.getParameterValues("hours");
            String[] customer_id = request.getParameterValues("customer_id");

            for (int i = 0; i < takeDate.length; i++)
            {
                Order temp = new Order(takeDate[i], startDate[i], Integer.parseInt(employee_id[i]), problemDesc[i],
                        repairDesc[i], status[i], Integer.parseInt(vehicle_id[i]), Double.parseDouble(price[i]),
                        Double.parseDouble(partsPrice[i]), Double.parseDouble(hoursPrice[i]),
                        Integer.parseInt(hours[i]), Integer.parseInt(customer_id[i]));
                orders.add(temp);
                temp.saveToDB(conn);

            }
            //              edit order

            String[] editId = request.getParameterValues("editId");

            for (int i = 0; i < editId.length; i++)
            {

                Order tmpOrder = new Order(Integer.parseInt(editId[i]));
                tmpOrder.setTakeDate(request.getParameterValues("editTakeDate")[i]);
                tmpOrder.setStartDate(request.getParameterValues("editStartDate")[i]);
                tmpOrder.setEmployee_id(Integer.parseInt(request.getParameterValues("editEmployee_id")[i]));
                tmpOrder.setProblemDesc(request.getParameterValues("editProblemDesc")[i]);
                tmpOrder.setRepairDesc(request.getParameterValues("editRepairDesc")[i]);
                tmpOrder.setStatus(request.getParameterValues("editStatus")[i]);
                tmpOrder.setVehicle_id(Integer.parseInt(request.getParameterValues("editVehicle_id")[i]));
                tmpOrder.setPrice(Double.parseDouble(request.getParameterValues("editPrice")[i]));
                tmpOrder.setPartsPrice(Double.parseDouble(request.getParameterValues("editPartsPrice")[i]));
                tmpOrder.setHoursPrice(Double.parseDouble(request.getParameterValues("editHoursPrice")[i]));
                tmpOrder.setHours(Integer.parseInt(request.getParameterValues("editHours")[i]));
                tmpOrder.setCustomer_id(Integer.parseInt(request.getParameterValues("editCustomer_id")[i]));

                tmpOrder.saveToDB(conn);

            }
            //              delete order

            String[] deleteId = request.getParameterValues("deleteId");

            for (int i = 0; i < deleteId.length; i++)
            {
                Order tmpOrder = new Order(Integer.parseInt(deleteId[i]));
                tmpOrder.DeleteOrder(conn);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
    }
}
