package gateway;

import manager.DbManager;
import dto.UserDTO;
import dto.CustomerDTO;
import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGateway
{
    public UserDTO find(int UserID)
    {
        UserDTO userDetails = null;
        
        try
        {            
            Connection conn = DbManager.getConnection();
            
            String sqlStr = ""
                    + "SELECT Users.*, Roles.NAME AS ROLE "
                    + "FROM Users "
                    + "JOIN UserRoles ON Users.id = UserRoles.USERID "
                    + "JOIN Roles ON UserRoles.ROLEID = Roles.ID "
                    + "WHERE Users.id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            
            stmt.setInt(1, UserID);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                userDetails = new UserDTO(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("hashedPassword"),
                        rs.getString("dateAdded"),
                        rs.getString("dateModified"),
                        rs.getString("addressLineOne"),
                        rs.getString("town"),
                        rs.getString("county"),
                        rs.getString("postcode"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("isActive"),
                        rs.getString("role")
                );
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return userDetails;
    }

    public CustomerDTO find(String name, String addressLine1, String zipCode)
    {
        CustomerDTO customerDetails = null;
        try
        {
            Connection conn = DbManager.getConnectionSample();
            
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

    public ArrayList<UserDTO> findAllSummaries()
    {
        ArrayList<UserDTO> userSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "SELECT Users.*, Roles.NAME AS ROLE "
                    + "FROM Users "
                    + "JOIN UserRoles ON Users.id = UserRoles.USERID "
                    + "JOIN Roles ON UserRoles.ROLEID = Roles.ID");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                UserDTO user = new UserDTO(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("hashedPassword"),
                        rs.getString("dateAdded"),
                        rs.getString("dateModified"),
                        rs.getString("addressLineOne"),
                        rs.getString("town"),
                        rs.getString("county"),
                        rs.getString("postcode"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("isActive"),
                        rs.getString("role")
                );
                
                userSummaries.add(user);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return userSummaries;
    }

    public boolean insert(CustomerDTO cust)
    {
        boolean insertOK = false;
        try
        {
            Connection conn = DbManager.getConnectionSample();
            
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

    public boolean delete(int CustID)
    {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean update(CustomerDTO cust)
    {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }
}
