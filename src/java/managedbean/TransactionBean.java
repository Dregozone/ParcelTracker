package managedbean;

import dto.OrderDTO;
import userUI.UserCommandFactory;
//import dto.OrderDTO;
//import dto.ParcelDTO;
import dto.TransactionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
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
    
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
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
    
    public String deleteTransaction(int transactionId, String role, OrderDTO orderDetails, TransactionDTO transaction) {
        
        try {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(""
                + "DELETE FROM Transactions "
                + "WHERE id = ? "
            );

            stmt.setInt(1, transactionId);

            stmt.executeUpdate();
            
            switch ( transaction.getName() ) {
                case "Picked up":
                    // Also change orders.driver back to id4 (None)
                    stmt = conn.prepareStatement(""
                        + "UPDATE Orders "
                        + "SET DriverID = 4 "
                        + "WHERE id = ? "
                    );

                    stmt.setInt(1, orderDetails.getId());
                    stmt.executeUpdate();
                    
                    break;
                    
                case "Dropped off":
                    // Also change orders.isComplete=false, orders.datecompleted=NULL
                    stmt = conn.prepareStatement(""
                        + "UPDATE Orders "
                        + "SET IsComplete = false, DateCompleted = NULL "
                        + "WHERE id = ? "
                    );

                    stmt.setInt(1, orderDetails.getId());
                    stmt.executeUpdate();
                    
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
        
        return "viewOrder_" + role;
    }
    
    public String addTransaction(int orderId, String transaction, int userId) {
        
        int nextId = getNextId();
        
        try
        {
            try {
                Connection conn = DbManager.getConnection();

                PreparedStatement stmt = conn.prepareStatement(""
                        + "INSERT INTO Transactions "
                        + "(id, orderid, name, addedby, dateAdded) "
                        + "VALUES "
                        + "(?, ?, ?, ?, ?)"
                );

                stmt.setInt(1, nextId);
                stmt.setInt(2, orderId);
                stmt.setString(3, transaction);
                stmt.setInt(4, userId);
                stmt.setDate(5, getDate() );

                stmt.executeUpdate();
                
                switch ( transaction ) {
                    case "Picked up": 
                        // also update Orders.DriverID
                        try {
                            stmt = conn.prepareStatement(""
                                    + "UPDATE Orders "
                                    + "SET driverID = ? "
                                    + "WHERE id = ? "
                            );

                            stmt.setInt(1, userId);
                            stmt.setInt(2, orderId);

                            stmt.executeUpdate();
                        } catch(SQLException sqle) {
                            sqle.printStackTrace();
                        }
                        
                        break;
                    case "Dropped off":
                        // also update Orders.IsComplete
                        try {
                            stmt = conn.prepareStatement(""
                                    + "UPDATE Orders "
                                    + "SET isComplete = true, dateCompleted = ? "
                                    + "WHERE id = ? "
                            );

                            stmt.setDate(1, getDate());
                            stmt.setInt(2, orderId);

                            stmt.executeUpdate();
                        } catch(SQLException sqle) {
                            sqle.printStackTrace();
                        }
                        
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
