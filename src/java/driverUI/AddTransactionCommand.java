package driverUI;

import dto.TransactionDTO;
import manager.TransactionManager;
import sellerUI.SellerCommand;

public class AddTransactionCommand implements DriverCommand
{
    private final TransactionDTO transactionDTO;
    private final TransactionManager transactionMgr;

    public AddTransactionCommand(TransactionDTO transactionDTO)
    {
        this.transactionDTO = transactionDTO;
        transactionMgr = new TransactionManager();
    }

    @Override
    public Object execute()
    {
        if (transactionMgr.addTransaction(transactionDTO))
        {
            return transactionMgr
                    .findTransaction( transactionDTO.getId() );
        }
        
        return null;
    }
}
