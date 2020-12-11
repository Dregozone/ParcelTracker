package Register_UI;

import dto.UserDTO;
import manager.UserManager;

public class CreateUserCommand implements RegisterCommand
{
    private final UserDTO userDTO;
    private final UserManager userMgr;

    public CreateUserCommand(UserDTO userDTO)
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
