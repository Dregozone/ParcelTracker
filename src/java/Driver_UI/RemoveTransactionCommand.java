package Driver_UI;

import dto.TransactionDTO;
import manager.TransactionManager;

public class RemoveTransactionCommand implements DriverCommand
{
    private final TransactionDTO transaction;
    private final TransactionManager transactionMgr;

    public RemoveTransactionCommand(TransactionDTO transaction)
    {
        this.transaction = transaction;
        transactionMgr = new TransactionManager();
    }

    @Override
    public Object execute()
    {
        if (transactionMgr.removeTransaction(transaction))
        {
            return transactionMgr
                    .findTransaction(transaction.getId());
        }
        
        return null;
    }
}
