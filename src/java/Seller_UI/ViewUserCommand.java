package Seller_UI;

import manager.UserManager;

public class ViewUserCommand implements SellerCommand
{
    private final int userId;
    private final UserManager userMgr;

    public ViewUserCommand(int userId)
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
