package sellerUI;

import manager.UserManager;

public class FindUserCommand implements SellerCommand
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
