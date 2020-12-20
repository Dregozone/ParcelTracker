package managedbean;

import Seller_UI.SellerCommandFactory;
import Recipient_UI.RecipientCommandFactory;
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
import javax.inject.Named;
import javax.inject.Inject;
import manager.DbManager;

@Named(value = "recipientBean")
@SessionScoped
public class RecipientBean implements Serializable
{
    private String role;
    private int id;
    private int recipientId;
    private int sellerId;
    
    private UserDTO userDetails = null;
    private OrderDTO orderDetails = null;
    private ParcelDTO parcelDetails = null;
    private int totalOrders = 0;
    private int totalParcels = 0;
    private int totalTransactions = 0;
    
    @Inject
    LoginBean loginBean;
    
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
    
    public ArrayList<TransactionDTO> viewOrderTransactions(int OrderID)
    {
        ArrayList<TransactionDTO> transactionSummaries
                = (ArrayList<TransactionDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ORDER_TRANSACTIONS,
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
    
    public ArrayList<ParcelDTO> viewOrderParcels(int OrderID)
    {
        ArrayList<ParcelDTO> parcelSummaries
                = (ArrayList<ParcelDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ORDER_PARCELS,
                                OrderID)
                        .execute();

        totalParcels = parcelSummaries.size();

        return parcelSummaries;
    }
    
    public ArrayList<OrderDTO> viewRecipientOrders(int UserID)
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_RECIPIENT_ORDERS,
                                UserID)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String viewOrderProgress(int orderID)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_ORDER_PROGRESS,
                                orderID)
                        .execute();

        return "viewOrder_" + findRoleByUser(loginBean.getId());
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
