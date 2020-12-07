package sellerUI;

import manager.OrderManager;

public class FindDeliveryMetricsCommand implements SellerCommand
{
    private final OrderManager orderMgr;

    public FindDeliveryMetricsCommand()
    {
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.findDeliveryMetrics();
    }
}
