package Register_UI;

import dto.UserDTO;

public class RegisterCommandFactory
{
    public static final int CREATE_USER = 1;

    public static RegisterCommand createCommand(int commandType, UserDTO userDTO)
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
