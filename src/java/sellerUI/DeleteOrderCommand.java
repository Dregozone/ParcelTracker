package sellerUI;

import manager.OrderManager;

public class DeleteOrderCommand implements SellerCommand
{
    private final int orderId;
    private final OrderManager orderMgr;

    public DeleteOrderCommand(int orderId)
    {
        this.orderId = orderId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        if (orderMgr.deleteOrder(orderId))
        {
            return orderMgr
                    .findOrder(orderId);
        }
        
        return null;
    }
}
