package Driver_UI;

import dto.TransactionDTO;

public class DriverCommandFactory
{
    public static final int VIEW_ALL_DELIVERIES = 6;
    public static final int ADD_TRANSACTION = 1;
    public static final int REMOVE_TRANSACTION = 2;
    
    public static DriverCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case VIEW_ALL_DELIVERIES:
                return new ViewAllDeliveriesCommand();
            default:
                return null;
        }
    }
        
    public static DriverCommand createCommand(int commandType, TransactionDTO transactionDTO)
    {
        switch (commandType)
        {
            case ADD_TRANSACTION:
                return new AddTransactionCommand(transactionDTO);
            case REMOVE_TRANSACTION:
                return new RemoveTransactionCommand(transactionDTO);
            default:
                return null;
        }
    }
}
