package managedbean;

import sellerUI.SellerCommandFactory;
import dto.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import manager.DbManager;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable
{    
    private UserDTO userDetails = null;
    private int totalUsers = 0;
    private String role = "";

    public ArrayList<UserDTO> getUserSummaries()
    {
        ArrayList<UserDTO> userSummaries
                = (ArrayList<UserDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.GET_USER_SUMMARIES)
                        .execute();

        totalUsers = userSummaries.size();

        return userSummaries;
    }

    public String fetchUserDetails(int userID)
    {
        userDetails
                = (UserDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.FIND_USER_BY_ID,
                                userID)
                        .execute();

        return "viewUser";
    }
    
    public UserDTO findUserDetailsById(int userID)
    {
        userDetails
                = (UserDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.FIND_USER_BY_ID,
                                userID)
                        .execute();

        return userDetails;
    }
    
    public String findRoleByUser(int userID)
    {
        
        try {        
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT R.name AS Role " + 
                    "FROM USERS U " +
                    "JOIN USERROLES UR ON U.ID = UR.userid " + 
                    "JOIN ROLES R ON UR.roleid = R.id " +
                    "WHERE U.ID = ? " + 
            "");
            
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                role = rs.getString("Role");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return role;
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public int getTotalUsers()
    {
        return totalUsers;
    }

    public String getRole() {
        return role;
    }
}
