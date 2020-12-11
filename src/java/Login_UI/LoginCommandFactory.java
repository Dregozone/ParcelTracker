package Login_UI;

public class LoginCommandFactory
{
    public static final int VIEW_USER_ROLE = 1;
    public static final int VIEW_USER_BY_USERNAME = 2;

    public static LoginCommand createCommand(int commandType, String username)
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
}
