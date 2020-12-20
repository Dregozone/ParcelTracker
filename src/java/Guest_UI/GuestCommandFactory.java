package Guest_UI;

import dto.UserDTO;

public class GuestCommandFactory
{
    public static final int VIEW_USER_ROLE = 1;
    public static final int VIEW_USER_BY_USERNAME = 2;
    public static final int CREATE_USER = 3;

    public static GuestCommand createCommand(int commandType, String username)
    {
        switch (commandType)
        {
            case VIEW_USER_ROLE: /* By username */
                return new ViewUserRoleCommand(username);
            case VIEW_USER_BY_USERNAME: 
                return new ViewUserByUsernameCommand(username);
            default:
                return null;
        }
    }
    
    public static GuestCommand createCommand(int commandType, UserDTO userDTO)
    {
        switch (commandType)
        {
            case CREATE_USER:
                return new CreateUserCommand(userDTO);
            default:
                return null;
        }
    }
}
