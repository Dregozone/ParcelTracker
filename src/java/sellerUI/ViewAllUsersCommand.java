package sellerUI;

import manager.UserManager;

public class ViewAllUsersCommand implements SellerCommand
{

    private final UserManager userMgr;

    public ViewAllUsersCommand()
    {
        userMgr = new UserManager();
    }

    @Override
    public Object execute()
    {
        return userMgr.getUserSummaries();
    }
}
