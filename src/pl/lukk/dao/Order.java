package pl.lukk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Order
{
    private int    id = 0;
    private String takeDate;
    private String startDate;
    private int    employee_id;
    private String problemDesc;
    private String repairDesc;
    private String status;
    private int    vehicle_id;
    private double price;
    private double partsPrice;
    private double hoursPrice;
    private int    hours;
    private int    customer_id;

    public Order()
    {
    }

    public Order(String takeDate, String startDate, int employee_id, String problemDesc, String repairDesc,
            String status, int vehicle_id, double price, double partsPrice, double hoursPrice, int hours,
            int customer_id)
    {
        super();
        this.takeDate = takeDate;
        this.startDate = startDate;
        this.employee_id = employee_id;
        this.problemDesc = problemDesc;
        this.repairDesc = repairDesc;
        this.status = status;
        this.vehicle_id = vehicle_id;
        this.price = price;
        this.partsPrice = partsPrice;
        this.hoursPrice = hoursPrice;
        this.hours = hours;
        this.customer_id = customer_id;
    }

    public Order(int id)
    {
        super();
        this.id = id;
    }

    public void DeleteOrder(Connection conn) throws SQLException
    {
        if (this.id != 0)
        {
            String sql = "DELETE FROM order WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.id);
            pst.executeUpdate();
            this.id = 0;																			                                                                         			// set deleted user id to 0
        }
    }

    @Override
    public String toString()                                                                                                                                                          // show information about user as string
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" ").append(this.getTakeDate()).append(" ").append(this.getStartDate())
                .append(" ").append(this.getEmployee_id()).append(" ").append(this.getProblemDesc()).append(" ")
                .append(this.getRepairDesc()).append(" ").append(this.getStatus()).append(" ")
                .append(this.getVehicle_id()).append(" ").append(this.getPrice()).append(" ")
                .append(this.getPartsPrice()).append(" ").append(this.getHoursPrice()).append(" ")
                .append(this.getHours()).append(" ").append(this.getCustomer_id()).append(" ");
        return sb.toString();
    }

    public Order saveToDB(Connection conn) throws SQLException
    {
        if (this.getId() == 0)																                                                                               				//	if id=0 then there is no such entry and it can be make
        {
            String[] generatedColumns =
            { "id" };									                                                                                                                               	//auto_increment
            String sql = "INSERT INTO order(takeDate, startDate, problemDesc, repairDesc, status, price, partsPrice, hoursPrice, hours, customer_id, vehicle_id, employee_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql, generatedColumns);					                                                                              		//	import of choosen columns (with auto_increment and foreign key
            pst.setString(1, this.getTakeDate());
            pst.setString(2, this.getStartDate());
            pst.setString(3, this.getProblemDesc());
            pst.setString(4, this.getRepairDesc());
            pst.setString(5, this.getStatus());
            pst.setDouble(6, this.getPrice());
            pst.setDouble(7, this.getPartsPrice());
            pst.setDouble(8, this.getHoursPrice());
            pst.setInt(9, this.getHours());
            pst.setInt(10, this.getCustomer_id());
            pst.setInt(11, this.getVehicle_id());
            pst.setInt(12, this.getEmployee_id());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();											                                                                               				//auto_increment and foreign keys
            if (rs.next())
            {
                this.id = rs.getInt(1);

            }
        }

        else 																							                                                                            	//	when entry exist this will update its data
        {
            String sql = "UPDATE order SET takeDate=?, startDate=?, problemDesc=?, repairDesc=?, status=?, price=?, partsPrice=?, hoursPrice=?, hours=?, customer_id=? vehicle_id=?, employee_id=? WHERE id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getTakeDate());
            pst.setString(2, this.getStartDate());
            pst.setString(3, this.getProblemDesc());
            pst.setString(4, this.getRepairDesc());
            pst.setString(5, this.getStatus());
            pst.setDouble(6, this.getPrice());
            pst.setDouble(7, this.getPartsPrice());
            pst.setDouble(8, this.getHoursPrice());
            pst.setInt(9, this.getHours());
            pst.setInt(10, this.getCustomer_id());
            pst.setInt(11, this.getVehicle_id());
            pst.setInt(12, this.getEmployee_id());
            pst.setInt(13, this.getId());
            pst.executeUpdate();
        }

        return this;
    }

    static public Order loadById(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM order WHERE id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next())
        {
            Order tmpOrd = new Order();
            tmpOrd.setTakeDate(rs.getString("takeDate"));
            tmpOrd.setStartDate(rs.getString("startDate"));
            tmpOrd.setEmployee_id(rs.getInt("employee_id"));
            tmpOrd.setProblemDesc(rs.getString("problemDesc"));
            tmpOrd.setRepairDesc(rs.getString("repairDesc"));
            tmpOrd.setStatus(rs.getString("status"));
            tmpOrd.setVehicle_id(rs.getInt("vehicle_id"));
            tmpOrd.setPrice(rs.getDouble("price"));
            tmpOrd.setPartsPrice(rs.getDouble("partsPrice"));
            tmpOrd.setHoursPrice(rs.getDouble("hoursPrice"));
            tmpOrd.setHours(rs.getInt("hours"));
            tmpOrd.setCustomer_id(rs.getInt("customer_id"));
            tmpOrd.id = rs.getInt("id");

            return tmpOrd;                                                                                                                                                          // return imported user with all his data
        }
        return null;                                                                                                                                                                // if there is no user with such id, return null
    }

    static public Order[] loadByEmployee(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM order WHERE employee_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        ArrayList<Order> orders = new ArrayList<Order>();

        while (rs.next())
        {

            Order tmpOrd = new Order();
            tmpOrd.setTakeDate(rs.getString("takeDate"));
            tmpOrd.setStartDate(rs.getString("startDate"));
            tmpOrd.setEmployee_id(rs.getInt("employee_id"));
            tmpOrd.setProblemDesc(rs.getString("problemDesc"));
            tmpOrd.setRepairDesc(rs.getString("repairDesc"));
            tmpOrd.setStatus(rs.getString("status"));
            tmpOrd.setVehicle_id(rs.getInt("vehicle_id"));
            tmpOrd.setPrice(rs.getDouble("price"));
            tmpOrd.setPartsPrice(rs.getDouble("partsPrice"));
            tmpOrd.setHoursPrice(rs.getDouble("hoursPrice"));
            tmpOrd.setHours(rs.getInt("hours"));
            tmpOrd.setCustomer_id(rs.getInt("customer_id"));
            tmpOrd.id = rs.getInt("id");

            orders.add(tmpOrd);                                                                                                                                                  // return imported user with all his data
        }
        Order[] ordersArr = new Order[orders.size()];
        orders.toArray(ordersArr);

        return ordersArr;                                                                                                                                                        // if there is no user with such id, return null
    }

    static public Order[] loadByCustomer(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM order WHERE customer_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        ArrayList<Order> orders = new ArrayList<Order>();

        while (rs.next())
        {

            Order tmpOrd = new Order();
            tmpOrd.setTakeDate(rs.getString("takeDate"));
            tmpOrd.setStartDate(rs.getString("startDate"));
            tmpOrd.setEmployee_id(rs.getInt("employee_id"));
            tmpOrd.setProblemDesc(rs.getString("problemDesc"));
            tmpOrd.setRepairDesc(rs.getString("repairDesc"));
            tmpOrd.setStatus(rs.getString("status"));
            tmpOrd.setVehicle_id(rs.getInt("vehicle_id"));
            tmpOrd.setPrice(rs.getDouble("price"));
            tmpOrd.setPartsPrice(rs.getDouble("partsPrice"));
            tmpOrd.setHoursPrice(rs.getDouble("hoursPrice"));
            tmpOrd.setHours(rs.getInt("hours"));
            tmpOrd.setCustomer_id(rs.getInt("customer_id"));
            tmpOrd.id = rs.getInt("id");

            orders.add(tmpOrd);                                                                                                                                                  // return imported user with all his data
        }
        Order[] ordersArr = new Order[orders.size()];
        orders.toArray(ordersArr);

        return ordersArr;                                                                                                                                                        // if there is no user with such id, return null
    }

    static public Order[] loadAll(Connection conn) throws SQLException
    {
        String sql = "SELECT * FROM order";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        while (rs.next())
        {

            Order tmpOrd = new Order();
            tmpOrd.setTakeDate(rs.getString("takeDate"));
            tmpOrd.setStartDate(rs.getString("startDate"));
            tmpOrd.setEmployee_id(rs.getInt("employee_id"));
            tmpOrd.setProblemDesc(rs.getString("problemDesc"));
            tmpOrd.setRepairDesc(rs.getString("repairDesc"));
            tmpOrd.setStatus(rs.getString("status"));
            tmpOrd.setVehicle_id(rs.getInt("vehicle_id"));
            tmpOrd.setPrice(rs.getDouble("price"));
            tmpOrd.setPartsPrice(rs.getDouble("partsPrice"));
            tmpOrd.setHoursPrice(rs.getDouble("hoursPrice"));
            tmpOrd.setHours(rs.getInt("hours"));
            tmpOrd.setCustomer_id(rs.getInt("customer_id"));
            tmpOrd.id = rs.getInt("id");

            orders.add(tmpOrd);                                                                                                                                                // return imported user with all his data
        }
        Order[] ordersArr = new Order[orders.size()];
        orders.toArray(ordersArr);

        return ordersArr;                                                                                                                                                       // if there is no user with such id, return null
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getTakeDate()
    {
        return takeDate;
    }

    public void setTakeDate(String takeDate)
    {
        this.takeDate = takeDate;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public int getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(int employee_id)
    {
        this.employee_id = employee_id;
    }

    public String getProblemDesc()
    {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc)
    {
        this.problemDesc = problemDesc;
    }

    public String getRepairDesc()
    {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc)
    {
        this.repairDesc = repairDesc;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getVehicle_id()
    {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id)
    {
        this.vehicle_id = vehicle_id;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPartsPrice()
    {
        return partsPrice;
    }

    public void setPartsPrice(double partsPrice)
    {
        this.partsPrice = partsPrice;
    }

    public double getHoursPrice()
    {
        return hoursPrice;
    }

    public void setHoursPrice(double hoursPrice)
    {
        this.hoursPrice = hoursPrice;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getId()
    {
        return id;
    }
}
