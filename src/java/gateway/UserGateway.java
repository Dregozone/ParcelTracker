package gateway;

import manager.DbManager;
import dto.UserDTO;
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
                    + "JOIN Roles ON UserRoles.ROLEID = Roles.ID "
                    + "WHERE Users.username <> 'None' ");
            
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
}
