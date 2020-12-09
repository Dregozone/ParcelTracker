package sellerUI;

import manager.OrderManager;

public class ViewDriverMetricsCommand implements SellerCommand
{
    private final OrderManager orderMgr;

    public ViewDriverMetricsCommand()
    {
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.viewDriverMetrics();
    }
}
