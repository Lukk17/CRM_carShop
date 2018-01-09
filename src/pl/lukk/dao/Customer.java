package pl.lukk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer
{
    private int    id = 0;
    private String name;
    private String surname;

    public Customer()
    {
    }

    public Customer(String name, String surname)
    {
        super();
        this.name = name;
        this.surname = surname;
    }

    public Customer(int id)
    {
        super();
        this.id = id;
    }

    @Override
    public String toString()                                                                                                    // show information about user as string
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" ").append(this.getName()).append(" ").append(this.getSurname()).append(" ");
        return sb.toString();
    }

    public Customer saveToDB(Connection conn) throws SQLException
    {
        if (this.getId() == 0)                                                                                                  //  if id=0 then there is no such entry and it can be make
        {
            String[] generatedColumns =
            { "id" };                                                                                                            //auto_increment
            String sql = "INSERT INTO customer (name, surname) VALUES ( ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql, generatedColumns);                                               //  import of choosen columns (with auto_increment and foreign key
            pst.setString(1, this.getName());
            pst.setString(2, this.getSurname());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();                                                                              //auto_increment and foreign keys
            if (rs.next())
            {
                this.id = rs.getInt(1);                                                                                         // "1" stands for first of imported column (in this its "id), set entry id same as imported from database
            }
        }

        else                                                                                                                    //  when entry exist this will update its data
        {
            String sql = "UPDATE customer SET name=?, surname=? WHERE id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.getName());
            pst.setString(2, this.getSurname());
            pst.setInt(7, this.getId());
            pst.executeUpdate();
        }
        return this;
    }

    public void DeleteCustomer(Connection conn) throws SQLException
    {
        if (this.id != 0)
        {
            String sql = "DELETE FROM customer WHERE id=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.id);
            pst.executeUpdate();
            this.id = 0;                                                                                                        // set deleted user id to 0
        }
    }

    static public Customer[] loadAll(Connection conn) throws SQLException
    {
        ArrayList<Customer> customer = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next())                                                                                                       // if there are more entries, import them
        {
            Customer tmpCustomer = new Customer();                                                                              //  create temporary user object to save data
            tmpCustomer.setName(rs.getString("name"));
            tmpCustomer.setSurname(rs.getString("surname"));
            tmpCustomer.id = rs.getInt("id");

            customer.add(tmpCustomer);                                                                                          // add temporary object to list
        }
        Customer[] customerArr = new Customer[customer.size()];                                                                 //  create table of users with size of list
        customer.toArray(customerArr);                                                                                          //  make array from list

        return customerArr;
    }

    public Customer loadById(Connection conn, int id) throws SQLException
    {
        String sql = "SELECT * FROM customer WHERE id=?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next())
        {
            Customer tmpCust = new Customer();
            tmpCust.setName(rs.getString("name"));
            tmpCust.setSurname(rs.getString("surname"));
            tmpCust.id = rs.getInt("id");

            return tmpCust;                                                                                                     // return imported user with all his data
        }
        return null;                                                                                                            // if there is no user with such id, return null
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

    public int getId()
    {
        return id;
    }
}
