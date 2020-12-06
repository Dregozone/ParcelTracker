package managedbean;

import sellerUI.SellerCommandFactory;
import recipientUI.RecipientCommandFactory;
import dto.OrderDTO;
import dto.ParcelDTO;
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

@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable
{
    private int id;
    private int recipientId;
    private int sellerId;
    
    private OrderDTO orderDetails = null;
    private ParcelDTO parcelDetails = null;
    private int totalOrders = 0;
    private int totalParcels = 0;
    
    @Inject
    UserBean userBean;
    
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
    
    public String viewDriverMetrics() {
        
        
        ////
        
        return "viewDriverMetrics";
    }
    
    public String deleteOrder(int orderId) {
        
        try {
            Connection conn = DbManager.getConnection();

            // Delete orderparcels against this order due to FK constraint
            PreparedStatement stmt = conn.prepareStatement("" + 
                "DELETE FROM OrderParcels OP " + 
                "WHERE OP.orderId = ? "
            );
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
            
            // Delete transactions against this order due to FK constraint
            stmt = conn.prepareStatement("" + 
                "DELETE FROM Transactions T " + 
                "WHERE T.orderId = ? "
            );
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
            
            // Delete the order itself
            stmt = conn.prepareStatement(""
                + "DELETE FROM Orders "
                + "WHERE id = ? "
            );
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return "Seller_UI";
    }
    
    public String addingParcelToOrder(int orderId) {
        
        this.id = orderId;
        
        ////
        
        return "addParcelToOrder";
    }
    
    public String addParcelToOrder(int orderId, int parcelId, int quantity) {
        
        try {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement("" + 
                "INSERT INTO OrderParcels " +
                "(id, orderId, parcelId, quantity, dateAdded) " + 
                "values " + 
                "(?, ?, ?, ?, ?) " +
            "");
            
            stmt.setInt(1, getNextOrderParcelsId());
            stmt.setInt(2, orderId);
            stmt.setInt(3, parcelId);
            stmt.setInt(4, quantity);
            stmt.setDate(5, getDate());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return "Seller_UI";
    }
    
    public String createOrder()
    {
        OrderDTO newOrder = new OrderDTO(
                                    getNextId(),
                                    userBean.findUserDetailsById(recipientId),
                                    userBean.findUserDetailsById(4),
                                    userBean.findUserDetailsById(sellerId),
                                    "", /* will be now() on insert */
                                    false,
                                    "" /* will be null on insert */
        );

        OrderDTO insertedOrder 
                = (OrderDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.CREATE_ORDER,
                                newOrder)
                        .execute();

        orderDetails = insertedOrder;

        return "Seller_UI";
    }

    public ArrayList<ParcelDTO> getOrderParcelByOrder(int OrderID)
    {
        ArrayList<ParcelDTO> parcelSummaries
                = (ArrayList<ParcelDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.GET_PARCEL_SUMMARIES_BY_ORDER,
                                OrderID)
                        .execute();

        totalParcels = parcelSummaries.size();

        return parcelSummaries;
    }
            
    public ArrayList<OrderDTO> getOrderSummaries()
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.GET_ORDER_SUMMARIES)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }
    
    public ArrayList<OrderDTO> getOrderSummariesByUser(int UserID)
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.GET_ORDER_SUMMARIES_BY_USER,
                                UserID)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String fetchOrderDetails(int orderID, String role)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.FIND_ORDER_BY_ID,
                                orderID)
                        .execute();

        return "viewOrder_" + role;
    }

    public int getTotalOrders()
    {
        return totalOrders;
    }

    public OrderDTO getOrderDetails() {
        return orderDetails;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
