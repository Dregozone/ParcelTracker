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
    
    public UserDTO findUserByUsername(String username)
    {
        return gateway.findUserByUsername(username);
    }
    
    public String findUserRole(String username)
    {
        return gateway.findUserRole(username);
    }

    public ArrayList<UserDTO> viewAllUsers()
    {
        return gateway.findAllUsers();
    }
    
    public boolean createUser(UserDTO user) {
        
        return gateway.insertUser(user);
    }
}
