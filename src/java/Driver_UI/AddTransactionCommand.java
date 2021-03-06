package Driver_UI;

import dto.TransactionDTO;
import manager.TransactionManager;

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
