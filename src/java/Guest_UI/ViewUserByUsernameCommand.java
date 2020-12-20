package Guest_UI;

import manager.UserManager;

public class ViewUserByUsernameCommand implements GuestCommand
{
    private final String username;
    private final UserManager userMgr;

    public ViewUserByUsernameCommand(String username)
    {
        this.username = username;
        userMgr = new UserManager();
    }

    @Override
    public Object execute()
    {
        return userMgr.findUserByUsername(username);
    }
}
