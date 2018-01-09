package pl.lukk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee
{
    private int    id = 0;
    private String name;
    private String surname;
    private String adress;
    private int    tel;
    private String note;
    private double hourPrice;

    public Employee()
    {
    }

    public Employee(String name, String surname, String adress, int tel, String note, double hourPrice)
    {
        super();

        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.tel = tel;
        this.note = note;
        this.hourPrice = hourPrice;
    }

    public Employee(int id)
    {
        super();
        this.id = id;
    }

    @Override
    public String toString()																									// show information about user as string
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" ").append(this.getName()).append(" ").append(this.getSurname()).append(" ")
                .append(this.getAdress()).append(" ").append(this.getTel()).append(" ").append(this.getNote())
                .append(" ").append(this.getHourPrice()).append(" ");
        return sb.toString();
    }

    public Employee saveToDB(Connection conn) throws SQLException
    {
        if (this.getId() == 0)																									//	if id=0 then there is no such entry and it can be make
        {
            String[] generatedColumns =
            { "id" };																				                           //auto_increment
            String sql = "INSERT INTO employee (name, surname, adress, tel, note, hourPrice) VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql, generatedColumns);												//	import of choosen columns (with auto_increment and foreign key
            pst.setString(1, this.getName());
            pst.setString(2, this.getSurname());
            pst.setString(3, this.getAdress());
            pst.setInt(4, this.getTel());
            pst.setString(5, this.getNote());
            pst.setDouble(6, this.getHourPrice());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();																				//auto_increment and foreign keys
            if (rs.next())
            {
                this.id = rs.getInt(1);																							// "1" stands for first of imported column (in this its "id), set entry id same as imported from database
            }
        }

        else 																													//	when entry exist this will update its data
        {
            String sql = "UPDATE employee SET name=?, surname=?, adress=?, tel=?, note=?, hourPrice=? WHERE id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getName());
            pst.setString(2, this.getSurname());
            pst.setString(3, this.getAdress());
            pst.setInt(4, this.getTel());
            pst.setString(5, this.getNote());
            pst.setDouble(6, this.getHourPrice());
            pst.setInt(7, this.getId());
            pst.executeUpdate();
        }
        return this;
    }

    public void DeleteEmployee(Connection conn) throws SQLException
    {
        if (this.id != 0)
        {
            String sql = "DELETE FROM employee WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.id);
            pst.executeUpdate();
            this.id = 0;																										// set deleted user id to 0
        }
    }

    static public Employee[] loadAll(Connection conn) throws SQLException
    {
        ArrayList<Employee> employee = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next())																										// if there are more entries, import them
        {
            Employee tmpEmpl = new Employee();																					//	create temporary user object to save data
            tmpEmpl.setName(rs.getString("name"));
            tmpEmpl.setSurname(rs.getString("surname"));
            tmpEmpl.setAdress(rs.getString("adress"));
            tmpEmpl.setTel(rs.getInt("tel"));
            tmpEmpl.setNote(rs.getString("note"));
            tmpEmpl.setHourPrice(rs.getDouble("hourPrice"));
            tmpEmpl.id = rs.getInt("id");

            employee.add(tmpEmpl);																								// add temporary object to list
        }
        Employee[] employeeArr = new Employee[employee.size()];																	//	create table of users with size of list
        employee.toArray(employeeArr);																							//	make array from list

        return employeeArr;
    }

    public Employee loadById(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM employee WHERE id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next())
        {
            Employee tmpEmpl = new Employee();
            tmpEmpl.setName(rs.getString("name"));
            tmpEmpl.setSurname(rs.getString("surname"));
            tmpEmpl.setAdress(rs.getString("adress"));
            tmpEmpl.setTel(rs.getInt("tel"));
            tmpEmpl.setNote(rs.getString("note"));
            tmpEmpl.setHourPrice(rs.getDouble("hourPrice"));
            tmpEmpl.id = rs.getInt("id");

            return tmpEmpl;																										// return imported user with all his data
        }
        return null;																											// if there is no user with such id, return null
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public int getTel()
    {
        return tel;
    }

    public void setTel(int tel)
    {
        this.tel = tel;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public double getHourPrice()
    {
        return hourPrice;
    }

    public void setHourPrice(double hourPrice)
    {
        this.hourPrice = hourPrice;
    }

    public int getId()
    {
        return id;
    }
}
