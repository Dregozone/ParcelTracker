package manager;

import dto.CustomerDTO;
import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager
{    
    public CustomerDTO findCustomer(int CustID)
    {
        CustomerDTO customerDetails = null;
        try
        {
            Connection conn = DbManager.getConnection();

            String sqlStr = "SELECT * "
                    + "FROM Customer JOIN Discount_Code ON Customer.Discount_Code = Discount_Code.Discount_Code "
                    + "WHERE Customer_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = new CustomerDTO(
                        rs.getInt("Customer_ID"),
                        new DiscountDTO(rs.getString("Discount_Code"), rs.getDouble("Rate")),
                        rs.getString("Name"),
                        rs.getString("AddressLine1"),
                        rs.getString("AddressLine2"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Zip"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return customerDetails;
    }

    public CustomerDTO findCustomer(String name, String addressLine1, String zipCode)
    {
        CustomerDTO customerDetails = null;
        try
        {
            Connection conn = DbManager.getConnection();
            
            String sqlStr = "SELECT * "
                    + "FROM Customer JOIN Discount_Code ON Customer.Discount_Code = Discount_Code.Discount_Code "
                    + "WHERE Name = ? AND AddressLine1 = ? AND Zip = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, name);
            stmt.setString(2, addressLine1);
            stmt.setString(3, zipCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = new CustomerDTO(
                        rs.getInt("Customer_ID"),
                        new DiscountDTO(rs.getString("Discount_Code"), rs.getDouble("Rate")),
                        rs.getString("Name"),
                        rs.getString("AddressLine1"),
                        rs.getString("AddressLine2"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Zip"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return customerDetails;
    }

    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        ArrayList<CustomerDTO> customerSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT Customer_ID, Name FROM Customer");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                CustomerDTO cust = new CustomerDTO(rs.getInt("Customer_ID"), null, rs.getString("Name"), "", "", "", "", "");
                customerSummaries.add(cust);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return customerSummaries;
    }

    public boolean insertCustomer(CustomerDTO cust)
    {
        boolean insertOK = false;
        try
        {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customer (customer_id, discount_code, zip, name, addressline1, addressline2, city, state) values (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, cust.getId());
            stmt.setString(2, cust.getDiscount().getCode());
            stmt.setString(3, cust.getZipCode());
            stmt.setString(4, cust.getName());
            stmt.setString(5, cust.getAddressLine1());
            stmt.setString(6, cust.getAddressLine2());
            stmt.setString(7, cust.getCity());
            stmt.setString(8, cust.getState());
            
            int rows = stmt.executeUpdate();
            
            insertOK = rows == 1;

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return insertOK;
    }

}
