package pl.lukk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Vehicle
{
    private int    id = 0;
    private String brand;
    private String model;
    private int    productionYear;
    private int    serialNum;
    private String nextCheckDate;
    private int    customer_id;

    public Vehicle()
    {
        super();
    }

    public Vehicle(String brand, String model, int productionYear, int serialNum, String nextCheckDate, int customer_id)
    {
        super();
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.serialNum = serialNum;
        this.nextCheckDate = nextCheckDate;
        this.customer_id = customer_id;
    }

    public Vehicle(int id)
    {
        super();
        this.id = id;
    }

    @Override
    public String toString()                                                                                                                                                            // show information about user as string
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" ").append(this.getBrand()).append(" ").append(this.getModel()).append(" ")
                .append(this.getProductionYear()).append(" ").append(this.getSerialNum()).append(" ")
                .append(this.getNextCheckDate()).append(" ").append(this.getCustomer_id()).append(" ");
        return sb.toString();
    }

    public void DeleteVehicle(Connection conn) throws SQLException
    {
        if (this.id != 0)
        {
            String sql = "DELETE FROM vehicle WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.id);
            pst.executeUpdate();
            this.id = 0;                                                                                                                                                                // set deleted user id to 0
        }
    }

    public Vehicle saveToDB(Connection conn) throws SQLException
    {
        if (this.getId() == 0)                                                                                                                                                          //  if id=0 then there is no such entry and it can be make
        {
            String[] generatedColumns =
            { "id" };                                                                                                                                                                   //auto_increment
            String sql = "INSERT INTO vehicle(brand, model, productionYear, serialNum, nextCheckDate, customer_id) VALUES ( ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql, generatedColumns);                                                                                                       //  import of choosen columns (with auto_increment and foreign key
            pst.setString(1, this.getBrand());
            pst.setString(2, this.getModel());
            pst.setInt(3, this.getProductionYear());
            pst.setInt(4, this.getSerialNum());
            pst.setString(5, this.getNextCheckDate());
            pst.setInt(6, this.getCustomer_id());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();                                                                                                                                      //auto_increment and foreign keys
            if (rs.next())
            {
                this.id = rs.getInt(1);

            }
        }

        else                                                                                                                                                                            //  when entry exist this will update its data
        {
            String sql = "UPDATE vehicle SET brand=?, model=?, productionYear=?, serialNum=?, nextCheckDate=?, customer_id=? WHERE id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getBrand());
            pst.setString(2, this.getModel());
            pst.setInt(3, this.getProductionYear());
            pst.setInt(4, this.getSerialNum());
            pst.setString(5, this.getNextCheckDate());
            pst.setInt(6, this.getCustomer_id());
            pst.setInt(7, this.getId());
            pst.executeUpdate();
        }

        return this;
    }

    static public Vehicle loadById(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM vehicle WHERE id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next())
        {
            Vehicle tmpVeh = new Vehicle();
            tmpVeh.setBrand(rs.getString("brand"));
            tmpVeh.setModel(rs.getString("model"));
            tmpVeh.setProductionYear(rs.getInt("productionYear"));
            tmpVeh.setSerialNum(rs.getInt("serialNum"));
            tmpVeh.setNextCheckDate(rs.getString("nextCheckDate"));
            tmpVeh.setCustomer_id(rs.getInt("customer_id"));
            tmpVeh.id = rs.getInt("id");

            return tmpVeh;                                                                                                                                                          // return imported user with all his data
        }
        return null;                                                                                                                                                                // if there is no user with such id, return null
    }

    static public Vehicle[] loadByCustomer(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM vehicle WHERE customer_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        while (rs.next())
        {

            Vehicle tmpVeh = new Vehicle();
            tmpVeh.setBrand(rs.getString("brand"));
            tmpVeh.setModel(rs.getString("model"));
            tmpVeh.setProductionYear(rs.getInt("productionYear"));
            tmpVeh.setSerialNum(rs.getInt("serialNum"));
            tmpVeh.setNextCheckDate(rs.getString("nextCheckDate"));
            tmpVeh.setCustomer_id(rs.getInt("customer_id"));
            tmpVeh.id = rs.getInt("id");

            vehicles.add(tmpVeh);                                                                                                                                                  // return imported user with all his data
        }
        Vehicle[] vehiclesArr = new Vehicle[vehicles.size()];
        vehicles.toArray(vehiclesArr);

        return vehiclesArr;                                                                                                                                                        // if there is no user with such id, return null
    }

    static public Vehicle[] loadAll(Connection conn) throws SQLException
    {
        String sql = "SELECT * FROM vehicle";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        while (rs.next())
        {

            Vehicle tmpVeh = new Vehicle();
            tmpVeh.setBrand(rs.getString("brand"));
            tmpVeh.setModel(rs.getString("model"));
            tmpVeh.setProductionYear(rs.getInt("productionYear"));
            tmpVeh.setSerialNum(rs.getInt("serialNum"));
            tmpVeh.setNextCheckDate(rs.getString("nextCheckDate"));
            tmpVeh.setCustomer_id(rs.getInt("customer_id"));
            tmpVeh.id = rs.getInt("id");

            vehicles.add(tmpVeh);                                                                                                                                                 // return imported user with all his data
        }
        Vehicle[] vehiclesArr = new Vehicle[vehicles.size()];
        vehicles.toArray(vehiclesArr);

        return vehiclesArr;                                                                                                                                                        // if there is no user with such id, return null
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public int getProductionYear()
    {
        return productionYear;
    }

    public void setProductionYear(int productionYear)
    {
        this.productionYear = productionYear;
    }

    public int getSerialNum()
    {
        return serialNum;
    }

    public void setSerialNum(int serialNum)
    {
        this.serialNum = serialNum;
    }

    public String getNextCheckDate()
    {
        return nextCheckDate;
    }

    public void setNextCheckDate(String nextCheckDate)
    {
        this.nextCheckDate = nextCheckDate;
    }

    public int getId()
    {
        return id;
    }
}
