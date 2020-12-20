package Guest_UI;

import dto.UserDTO;
import manager.UserManager;

public class RegisterCommand implements GuestCommand
{
    private final UserDTO userDTO;
    private final UserManager userMgr;

    public RegisterCommand(UserDTO userDTO)
    {
        this.userDTO = userDTO;
        userMgr = new UserManager();
    }

    @Override
    public Object execute()
    {
        if (userMgr.createUser(userDTO))
        {
            return userMgr
                    .findUser( userDTO.getId() );
        }
        
        return null;
    }
}
