package Guest_UI;

import manager.UserManager;

public class ViewUserRoleCommand implements GuestCommand
{
    private final String username;
    private final UserManager userMgr;

    public ViewUserRoleCommand(String username)
    {
        this.username = username;
        userMgr = new UserManager();
    }

    @Override
    public String execute()
    {
        return userMgr.findUserRole(username);
    }
}
