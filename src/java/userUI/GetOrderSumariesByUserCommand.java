package userUI;

import manager.OrderManager;

public class GetOrderSumariesByUserCommand implements UserCommand
{    
    private final int userId;       
    private final OrderManager orderMgr;

    public GetOrderSumariesByUserCommand(int userId)
    {
        this.userId = userId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.getOrderSummariesByUser(userId);
    }
}
