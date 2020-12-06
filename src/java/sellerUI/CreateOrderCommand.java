package sellerUI;

import dto.OrderDTO;
import manager.OrderManager;
import recipientUI.RecipientCommand;
import recipientUI.RecipientCommand;

public class CreateOrderCommand implements SellerCommand
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
