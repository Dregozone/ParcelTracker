package userUI;

import manager.OrderManager;

public class GetParcelSumariesByOrderCommand implements UserCommand
{    
    private final int orderId;       
    private final OrderManager orderMgr;

    public GetParcelSumariesByOrderCommand(int orderId)
    {
        this.orderId = orderId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.getParcelSummariesByOrder(orderId);
    }
}
