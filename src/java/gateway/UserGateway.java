package gateway;

import manager.DbManager;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UserGateway
{
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }
    
    public boolean insertUser(UserDTO user)
    {
        boolean insertOK = false;
        
        try
        {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(""
                    + "INSERT INTO Users "
                    + "(id, firstname, lastname, username, hashedpassword, dateadded, datemodified, addresslineone, town, county, postcode, email, phone, isactive) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE, ?, ?, ?, ?, ?, ?, true)"
            );

            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getHashedPassword());
            stmt.setString(6, user.getAddressLineOne());
            stmt.setString(7, user.getTown());
            stmt.setString(8, user.getCounty());
            stmt.setString(9, user.getPostcode());
            stmt.setString(10, user.getEmail());
            stmt.setString(11, user.getPhone());

            stmt.executeUpdate();

            stmt = conn.prepareStatement(""
                    + "INSERT INTO UserRoles "
                    + "(id, userid, roleid, dateadded) "
                    + "VALUES "
                    + "(?, ?, ?, ?)"
            );

            stmt.setInt(1, user.getId());
            stmt.setInt(2, user.getId());
            stmt.setInt(3, 1); /* Recipient */
            stmt.setDate(4, getDate() );

            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return insertOK;
    }
    
    public String findUserRole(String username) {
        
        String role = "Recipient"; // Default
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "SELECT R.name AS ROLE, U.id "
                    + "FROM UserRoles UR "
                    + "INNER JOIN Users U ON UR.UserID = U.ID "
                    + "INNER JOIN Roles R ON UR.RoleID = R.ID "
                    + "WHERE U.username = ?"
            );
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();

            if ( rs.next() ) {
                role = rs.getString("ROLE");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return role;
    }
    
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
    
    public UserDTO findUserByUsername(String username)
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
                    + "WHERE Users.username = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            
            stmt.setString(1, username);
            
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

    public ArrayList<UserDTO> findAllUsers()
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
