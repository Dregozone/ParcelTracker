package sellerUI;

import manager.UserManager;

public class GetUserSumariesCommand implements SellerCommand
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
