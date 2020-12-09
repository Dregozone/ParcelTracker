package sellerUI;

import dto.OrderDTO;
import manager.OrderManager;

public class EditOrderCommand implements SellerCommand
{
    private final OrderDTO orderDTO;
    private final OrderManager orderMgr;

    public EditOrderCommand(OrderDTO orderDTO)
    {
        this.orderDTO = orderDTO;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        if (orderMgr.editOrder(orderDTO))
        {
            return orderMgr
                    .findOrder( orderDTO.getId() );
        }
        
        return null;
    }
}
