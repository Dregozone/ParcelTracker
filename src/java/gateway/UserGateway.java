package gateway;

import manager.DbManager;
import dto.UserDTO;
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

    /*
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
    */
}
