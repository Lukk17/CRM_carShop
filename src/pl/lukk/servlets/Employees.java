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

import pl.lukk.dao.Employee;
import pl.lukk.dbUtil.Make_database;
import pl.lukk.dbUtil.SQL_connection;

@WebServlet("/employees")
public class Employees extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {

            Employee[] employees = Employee.loadAll(conn);

            request.setAttribute("employees", employees);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try (Connection conn = SQL_connection.sqlConnect(Make_database.newDbName))
        {
            List<Employee> employees = Arrays.asList(Employee.loadAll(conn));                                                                                               // make list with all loaded employees from db
            request.setAttribute("employees", employees);

            //              add employee
            String[] name = request.getParameterValues("name");
            String[] surname = request.getParameterValues("surname");
            String[] adress = request.getParameterValues("adress");
            String[] tel = request.getParameterValues("tel");
            String[] note = request.getParameterValues("note");
            String[] hourPrice = request.getParameterValues("hourPrice");

            for (int i = 0; i < name.length; i++)
            {
                Employee temp = new Employee(name[i], surname[i], adress[i], Integer.parseInt(tel[i]), note[i],
                        Double.parseDouble(hourPrice[i]));
                employees.add(temp);
                temp.saveToDB(conn);

            }
            //              edit employee

            String[] editId = request.getParameterValues("editId");

            for (int i = 0; i < editId.length; i++)
            {

                Employee tmpEmploy = new Employee(Integer.parseInt(editId[i]));                                                                                                   //edit employee with given id
                tmpEmploy.setName(request.getParameterValues("editName")[i]);                                                                                                     //set name with value given in form (with same column as id - [i])
                tmpEmploy.setSurname(request.getParameterValues("editSurname")[i]);
                tmpEmploy.setAdress(request.getParameterValues("editAdress")[i]);
                tmpEmploy.setTel(Integer.parseInt(request.getParameterValues("editTel")[i]));
                tmpEmploy.setNote(request.getParameterValues("editNote")[i]);
                tmpEmploy.setHourPrice(Double.parseDouble(request.getParameterValues("editHourPrice")[i]));

                tmpEmploy.saveToDB(conn);

            }
            //              delete employee

            String[] deleteId = request.getParameterValues("deleteId");

            for (int i = 0; i < deleteId.length; i++)
            {
                Employee tmpEmploy = new Employee(Integer.parseInt(deleteId[i]));
                tmpEmploy.DeleteEmployee(conn);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
