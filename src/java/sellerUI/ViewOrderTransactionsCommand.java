package sellerUI;

import manager.TransactionManager;

public class ViewOrderTransactionsCommand implements SellerCommand
{    
    private final int orderId;       
    private final TransactionManager transactionMgr;

    public ViewOrderTransactionsCommand(int orderId)
    {
        this.orderId = orderId;
        transactionMgr = new TransactionManager();
    }

    @Override
    public Object execute()
    {
        return transactionMgr.viewOrderTransactions(orderId);
    }
}
