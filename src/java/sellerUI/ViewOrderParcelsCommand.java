package sellerUI;

import manager.OrderManager;

public class ViewOrderParcelsCommand implements SellerCommand
{    
    private final int orderId;       
    private final OrderManager orderMgr;

    public ViewOrderParcelsCommand(int orderId)
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
