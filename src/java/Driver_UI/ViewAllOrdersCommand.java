package Driver_UI;

import manager.OrderManager;

public class ViewAllOrdersCommand implements DriverCommand
{
    private final OrderManager orderMgr;

    public ViewAllOrdersCommand()
    {
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.viewAllOrders();
    }
}
