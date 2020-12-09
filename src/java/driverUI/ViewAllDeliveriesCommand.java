package driverUI;

import manager.OrderManager;

public class ViewAllDeliveriesCommand implements DriverCommand
{
    private final OrderManager orderMgr;

    public ViewAllDeliveriesCommand()
    {
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.getOrderSummaries();
    }
}
