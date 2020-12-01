package managedbean;

import userUI.UserCommandFactory;
//import dto.OrderDTO;
//import dto.ParcelDTO;
import dto.TransactionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "transactionBean")
@SessionScoped
public class TransactionBean implements Serializable
{
    private TransactionDTO transactionDetails = null;
    private int totalTransactions = 0;

    public ArrayList<TransactionDTO> getTransactionByOrder(int OrderID)
    {
        ArrayList<TransactionDTO> transactionSummaries
                = (ArrayList<TransactionDTO>) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.GET_TRANSACTION_SUMMARIES_BY_ORDER,
                                OrderID)
                        .execute();

        totalTransactions = transactionSummaries.size();

        return transactionSummaries;
    }
    
    public int getTotalTransactions() {
        
        return totalTransactions;
    }
    
    public TransactionDTO getTransactionDetails() {
        
        return transactionDetails;
    }
}
