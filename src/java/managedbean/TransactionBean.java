package managedbean;

import userUI.UserCommandFactory;
//import dto.OrderDTO;
//import dto.ParcelDTO;
import dto.TransactionDTO;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import manager.DbManager;

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
    
    public int getNextId() {

        int nextId = 0;
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT ID+1 AS ID FROM Transactions ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

            ResultSet rs = stmt.executeQuery();

            if ( rs.next() ) {
                nextId = rs.getInt("ID");
            }

            rs.close();
            stmt.close();
            conn.close();
            
        } catch ( SQLException sqle ) {
            sqle.printStackTrace();
        }
        
        return nextId;
    }
    
    public String addTransaction(int orderId, String transaction, int userId) {
        
        int nextId = getNextId();
        
        try
        {
            try {
                Connection conn = DbManager.getConnection();

                PreparedStatement stmt = conn.prepareStatement(""
                        + "INSERT INTO Transactions "
                        + "(id, orderid, name, addedby) "
                        + "VALUES "
                        + "(?, ?, ?, ?)"
                );

                stmt.setInt(1, nextId);
                stmt.setInt(2, orderId);
                stmt.setString(3, transaction);
                stmt.setInt(4, userId);

                stmt.executeUpdate();
                
                switch ( transaction ) {
                    case "Picked up": 
                        // also update Orders.DriverID
                        break;
                    case "Dropped off":
                        // also update Orders.IsComplete
                        break;
                    default:
                        //
                        break;
                }

                stmt.close();
                conn.close();
            } catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, e.toString());
        }
        
        return "Driver_UI"; // + orderId + ": " + nextId
    }
    
    public int getTotalTransactions() {
        
        return totalTransactions;
    }
    
    public TransactionDTO getTransactionDetails() {
        
        return transactionDetails;
    }
}
