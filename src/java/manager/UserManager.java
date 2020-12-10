package manager;

import dto.UserDTO;
import gateway.UserGateway;
import java.util.ArrayList;

public class UserManager
{
    private UserGateway gateway = new UserGateway();
    
    public UserDTO findUser(int UserID)
    {
        return gateway.find(UserID);
    }

    public ArrayList<UserDTO> viewAllUsers()
    {
        return gateway.findAllUsers();
    }
}
