package managedbean;

import recipientUI.RecipientCommandFactory;
import sellerUI.SellerCommandFactory;
import driverUI.DriverCommandFactory;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import manager.DbManager;

@Named(value = "driverBean")
@SessionScoped
public class DriverBean implements Serializable
{
    private String role;
    private int id;
    private int recipientId;
    private int sellerId;
    
    private UserDTO userDetails = null;
    private OrderDTO orderDetails = null;
    private ParcelDTO parcelDetails = null;
    private TransactionDTO transactionDetails = null;
            
    private int totalOrders = 0;
    private int totalParcels = 0;
    private int totalTransactions = 0;
    
    @Inject 
    LoginBean loginBean;
    
    public UserDTO findUserDetailsById(int userID)
    {
        userDetails
                = (UserDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_USER,
                                userID)
                        .execute();

        return userDetails;
    }
    
    public String findRoleByUser(int userID)
    {
        
        try {        
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT R.name AS Role " + 
                    "FROM USERS U " +
                    "JOIN USERROLES UR ON U.ID = UR.userid " + 
                    "JOIN ROLES R ON UR.roleid = R.id " +
                    "WHERE U.ID = ? " + 
            "");
            
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                role = rs.getString("Role");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return role;
    }
    
    public String deleteTransaction(int transactionId, String role, OrderDTO orderDetails, TransactionDTO transaction) {

        DriverCommandFactory
            .createCommand(DriverCommandFactory.REMOVE_TRANSACTION,
                    transaction)
            .execute();
        
        return "viewOrder_" + findRoleByUser(loginBean.getId());
    }
    
    public int getNextTransactionId() {

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
        
        int nextId = getNextTransactionId();
        
        TransactionDTO transactionDTO = new TransactionDTO(nextId, orderId, transaction, findUserDetailsById(userId), "" /*getDate()*/ );
        
        TransactionDTO insertedTransaction 
                = (TransactionDTO) DriverCommandFactory
                        .createCommand(DriverCommandFactory.ADD_TRANSACTION,
                                transactionDTO)
                        .execute();

        transactionDetails = insertedTransaction;
        
        return "Driver_UI";
    }
    
    public ArrayList<TransactionDTO> getTransactionByOrder(int OrderID)
    {
        ArrayList<TransactionDTO> transactionSummaries
                = (ArrayList<TransactionDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ORDER_TRANSACTIONS,
                                OrderID)
                        .execute();

        totalTransactions = transactionSummaries.size();

        return transactionSummaries;
    }
    
    public int getNextOrderParcelsId() {

        int nextId = 0;
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT ID+1 AS ID FROM OrderParcels ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

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
    
    public int getNextId() {

        int nextId = 0;
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT ID+1 AS ID FROM Orders ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

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
    
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }

    public ArrayList<ParcelDTO> getOrderParcelByOrder(int OrderID)
    {
        ArrayList<ParcelDTO> parcelSummaries
                = (ArrayList<ParcelDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ORDER_PARCELS,
                                OrderID)
                        .execute();

        totalParcels = parcelSummaries.size();

        return parcelSummaries;
    }
            
    public ArrayList<OrderDTO> getOrderSummaries()
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) DriverCommandFactory
                        .createCommand(DriverCommandFactory.VIEW_ALL_DELIVERIES)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String fetchOrderDetails(int orderID, String role)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_DELIVERY_PROGRESS,
                                orderID)
                        .execute();

        return "viewOrder_" + findRoleByUser(loginBean.getId());
    }
    
    public OrderDTO findOrderById(int orderID)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_DELIVERY_PROGRESS,
                                orderID)
                        .execute();

        return orderDetails;
    }

    public int getTotalOrders()
    {
        return totalOrders;
    }

    public OrderDTO getOrderDetails() {
        return orderDetails;
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public TransactionDTO getTransactionDetails() {
        return transactionDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public ParcelDTO getParcelDetails() {
        return parcelDetails;
    }

    public void setParcelDetails(ParcelDTO parcelDetails) {
        this.parcelDetails = parcelDetails;
    }

    public int getTotalParcels() {
        return totalParcels;
    }

    public void setTotalParcels(int totalParcels) {
        this.totalParcels = totalParcels;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
