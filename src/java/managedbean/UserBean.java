package managedbean;

import userUI.UserCommandFactory;
import dto.UserDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable
{    
    private UserDTO userDetails = null;
    private int totalUsers = 0;

    public ArrayList<UserDTO> getUserSummaries()
    {
        ArrayList<UserDTO> userSummaries
                = (ArrayList<UserDTO>) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.GET_USER_SUMMARIES)
                        .execute();

        totalUsers = userSummaries.size();

        return userSummaries;
    }

    public String fetchUserDetails(int userID)
    {
        userDetails
                = (UserDTO) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.FIND_USER_BY_ID,
                                userID)
                        .execute();

        return "viewUser";
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public int getTotalUsers()
    {
        return totalUsers;
    }
}
