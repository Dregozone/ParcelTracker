package sellerUI;

import manager.TransactionManager;

public class GetTransactionSumariesByOrderCommand implements SellerCommand
{    
    private final int orderId;       
    private final TransactionManager transactionMgr;

    public GetTransactionSumariesByOrderCommand(int orderId)
    {
        this.orderId = orderId;
        transactionMgr = new TransactionManager();
    }

    @Override
    public Object execute()
    {
        return transactionMgr.getTransactionSummariesByOrder(orderId);
    }
}
