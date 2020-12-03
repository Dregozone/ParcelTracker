package userUI;

import dto.OrderDTO;
import manager.OrderManager;

public class CreateOrderCommand implements UserCommand
{

    private final OrderDTO orderDTO;
    private final OrderManager orderMgr;

    public CreateOrderCommand(OrderDTO orderDTO)
    {
        this.orderDTO = orderDTO;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        if (orderMgr.createOrder(orderDTO))
        {
            return orderMgr
                    .findOrder( orderDTO.getId() );
        }
        
        return null;
    }
}
