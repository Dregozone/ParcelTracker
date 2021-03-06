package managedbean;

import Driver_UI.DriverCommandFactory;
import Seller_UI.SellerCommandFactory;
import Recipient_UI.RecipientCommandFactory;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.MetricDTO;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import manager.DbManager;

@Named(value = "sellerBean")
@SessionScoped
public class SellerBean implements Serializable
{
    private int id;
    private int recipientId;
    private int sellerId;
    
    private String role;
    private String name = "";
    private String type = "";
    private int weightGrams = 0;
    private int quantity = 0;
    
    private OrderDTO orderDetails = null;
    private ParcelDTO parcelDetails = null;
    private UserDTO userDetails = null;
    private int totalOrders = 0;
    private int totalParcels = 0;
    private int totalTransactions = 0;
    private int totalUsers = 0;
    
    @Inject
    LoginBean loginBean;
    
    /** Validate that this user ID exists on the system to avoid a null pointer exception
     * 
     * @param context
     * @param component
     * @param value 
     */
    public void validateUserId (FacesContext context,
                            UIComponent component,
                            Object value)
    {
        int userId = (int)value;
        
        if ( userId < 0 ) { // All userIDs are positive
            
            ((UIInput)component).setValid(false);
            
            context.addMessage(
                component.getClientId(),
                new FacesMessage("Please enter a valid user ID"));
        }
    }
    
    public UserDTO findUser(int userID)
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
    
    public ArrayList<UserDTO> viewAllUsers()
    {
        ArrayList<UserDTO> userSummaries
                = (ArrayList<UserDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ALL_USERS)
                        .execute();

        totalUsers = userSummaries.size();

        return userSummaries;
    }
    
    public String viewUser(int userID)
    {
        userDetails
                = (UserDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_USER,
                                userID)
                        .execute();

        return "viewUser";
    }
    
    public String edittingParcel(int parcelId) {
        
        parcelDetails = findParcelById(parcelId);
        
        name = parcelDetails.getName();
        type = parcelDetails.getType();
        weightGrams = parcelDetails.getWeightGrams();
        sellerId = parcelDetails.getSeller().getId();
        
        return "editParcel";
    }
    
    public String editParcel(int parcelId) {
        
        if ( weightGrams >= 0 ) { /* This is legal*/
            
            ParcelDTO edittedParcel = new ParcelDTO(
                                    parcelId,
                                    name,
                                    type,
                                    weightGrams,
                                    findUser(sellerId),
                                    "", /* will be now() on insert */
                                    "",
                                    0  /* quantityInOrder placeholder */
            );

            ParcelDTO newParcel 
                    = (ParcelDTO) SellerCommandFactory
                            .createCommand(SellerCommandFactory.EDIT_PARCEL,
                                    edittedParcel)
                            .execute();

            parcelDetails = newParcel;
        }
        
        return "Seller_UI";
    }
    
    public String deleteParcel(int parcelId) {
        
        SellerCommandFactory
            .createCommand(SellerCommandFactory.DELETE_PARCEL,
                    parcelId)
            .execute();

        return "Seller_UI";
    }
    
    public String createParcel()
    {        
        if ( weightGrams < 0 || sellerId < 0 ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please check values entered."));
        } else {
        
            ParcelDTO newParcel = new ParcelDTO(
                                        getNextParcelId(),
                                        name,
                                        type,
                                        weightGrams,
                                        findUser(sellerId),
                                        "", /* will be now() on insert */
                                        "",
                                        0  /* quantityInOrder placeholder */
            );

            ParcelDTO insertedParcel 
                    = (ParcelDTO) SellerCommandFactory
                            .createCommand(SellerCommandFactory.CREATE_PARCEL,
                                    newParcel)
                            .execute();

            parcelDetails = insertedParcel;
        }
            
        return "Seller_UI";
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
            
    public ArrayList<ParcelDTO> viewAllParcels()
    {
        ArrayList<ParcelDTO> parcelSummaries
                = (ArrayList<ParcelDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_ALL_PARCELS)
                        .execute();

        totalParcels = parcelSummaries.size();

        return parcelSummaries;
    }

    public ParcelDTO findParcelById(int parcelId) {
        
        parcelDetails
                = (ParcelDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_PARCEL,
                                parcelId)
                        .execute();
        
        return parcelDetails;
    }
    
    public String fetchParcelDetails(int parcelID)
    {
        parcelDetails
                = (ParcelDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.VIEW_PARCEL,
                                parcelID)
                        .execute();

        return "viewParcel";
    }
    
    public int getNextParcelId() {

        int nextId = 0;
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT ID+1 AS ID FROM Parcels ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

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
    
    public ArrayList<MetricDTO> viewDriverMetrics() {
        
        ArrayList<MetricDTO> deliveryMetrics
            = (ArrayList<MetricDTO>) SellerCommandFactory
                    .createCommand(SellerCommandFactory.VIEW_DRIVER_METRICS)
                    .execute();

        return deliveryMetrics;
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
    
    public int getNextOrderId() {

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
    
    public String edittingOrder(int orderId) {
        
        id = orderId;
        orderDetails = findOrderById(orderId);
        
        recipientId = orderDetails.getRecipient().getId();
        sellerId = orderDetails.getSeller().getId();
        
        return "editOrder";
    }
    
    public String editOrder(int orderId) {
        
        OrderDTO order = new OrderDTO(
                                    orderId,
                                    findUser(recipientId),
                                    findUser(4),
                                    findUser(sellerId),
                                    "", /* will be now() on insert */
                                    false,
                                    "" /* will be null on insert */
        );

        OrderDTO edittedOrder 
                = (OrderDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.EDIT_ORDER,
                                order)
                        .execute();

        orderDetails = edittedOrder;
        
        return "Seller_UI";
    }
    
    public String deleteOrder(int orderId) {
        
        /*OrderDTO order 
                = (OrderDTO)*/ SellerCommandFactory
                        .createCommand(SellerCommandFactory.DELETE_ORDER,
                                orderId)
                        .execute();
        
        return "Seller_UI";
    }
    
    public String addingParcelToOrder(int orderId) {
        
        this.id = orderId;
        
        return "addParcelToOrder";
    }
    
    public String addParcelToOrder(int orderId, int parcelId, int quantity) {
        
        if ( 
            orderId < 0 ||
            parcelId < 0 ||
            quantity < 0
        ) {
            
            throw new NullPointerException(); // This is illegal values, dont perform DB action, instead throw error back to application
        }
        
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
            
            return null;
        }
        
        return "Seller_UI";
    }
    
    public String createOrder()
    {
        OrderDTO newOrder = new OrderDTO(
                                    getNextOrderId(),
                                    findUser(recipientId),
                                    findUser(4),
                                    findUser(sellerId),
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
       
    public ArrayList<OrderDTO> viewAllOrders()
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) DriverCommandFactory
                        .createCommand(DriverCommandFactory.VIEW_ALL_ORDERS)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String fetchOrderDetails(int orderID, String role)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_ORDER_PROGRESS,
                                orderID)
                        .execute();

        return "viewOrder_" + findRoleByUser(loginBean.getId());
    }
    
    public OrderDTO findOrderById(int orderID)
    {
        orderDetails
                = (OrderDTO) RecipientCommandFactory
                        .createCommand(RecipientCommandFactory.VIEW_ORDER_PROGRESS,
                                orderID)
                        .execute();

        return orderDetails;
    }

    public int getTotalOrders()
    {
        return totalOrders;
    }
    
    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public OrderDTO getOrderDetails() {
        return orderDetails;
    }
    
    public void setOrderDetails(OrderDTO orderDetails) {
        this.orderDetails = orderDetails;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(int weightGrams) {        
        this.weightGrams = weightGrams;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDTO userDetails) {
        this.userDetails = userDetails;
    }
}
