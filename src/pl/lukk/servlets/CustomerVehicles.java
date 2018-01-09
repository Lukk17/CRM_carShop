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

import pl.lukk.dao.Vehicle;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet("/customerVehicles")
public class CustomerVehicles extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            Vehicle[] temp = Vehicle.loadByCustomer(conn, id);

            request.setAttribute("vahicles", temp);
            request.setAttribute("customer_id", id);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/customerVehicles.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            List<Vehicle> vehicles = Arrays.asList(Vehicle.loadAll(conn));
            request.setAttribute("vehicles", vehicles);

            //              add vehicle
            String[] brand = request.getParameterValues("brand");
            String[] model = request.getParameterValues("model");
            String[] productionYear = request.getParameterValues("productionYear");
            String[] serialNum = request.getParameterValues("serialNum");
            String[] nextCheckDate = request.getParameterValues("nextCheckDate");
            int customer_id = (int) request.getAttribute("customer_id");

            for (int i = 0; i < model.length; i++)
            {
                Vehicle temp = new Vehicle(brand[i], model[i], Integer.parseInt(productionYear[i]),
                        Integer.parseInt(serialNum[i]), nextCheckDate[i], customer_id);
                vehicles.add(temp);
                temp.saveToDB(conn);

            }

            //              edit vehicle
            String[] editId = request.getParameterValues("editId");

            for (int i = 0; i < editId.length; i++)
            {

                Vehicle tmpVehicle = new Vehicle(Integer.parseInt(editId[i]));
                tmpVehicle.setBrand(request.getParameterValues("editBrand")[i]);
                tmpVehicle.setModel(request.getParameterValues("editModel")[i]);
                tmpVehicle.setProductionYear(Integer.parseInt(request.getParameterValues("editProductionYear")[i]));
                tmpVehicle.setSerialNum(Integer.parseInt(request.getParameterValues("editSerialNum")[i]));
                tmpVehicle.setNextCheckDate(request.getParameterValues("editNextCheckDate")[i]);
                tmpVehicle.setCustomer_id(Integer.parseInt(request.getParameterValues("editCustomer_id")[i]));

                tmpVehicle.saveToDB(conn);

            }

            //              delete vehicle
            String[] deleteId = request.getParameterValues("deleteId");

            for (int i = 0; i < deleteId.length; i++)
            {
                Vehicle tmpVehicle = new Vehicle(Integer.parseInt(deleteId[i]));
                tmpVehicle.DeleteVehicle(conn);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/customerVehicles.jsp").forward(request, response);
    }
}
