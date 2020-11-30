package userUI;

import manager.UserManager;

public class GetUserSumariesCommand implements UserCommand
{

    private final UserManager userMgr;

    public GetUserSumariesCommand()
    {
        userMgr = new UserManager();
    }

    @Override
    public Object execute()
    {
        return userMgr.getUserSummaries();
    }
}
