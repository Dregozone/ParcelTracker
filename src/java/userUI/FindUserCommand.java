package userUI;

import manager.UserManager;

public class FindUserCommand implements UserCommand
{

    private final int userId;
    private final UserManager userMgr;

    public FindUserCommand(int userId)
    {
        this.userId = userId;
        userMgr = new UserManager();

    }

    @Override
    public Object execute()
    {
        return userMgr.findUser(userId);
    }
}
