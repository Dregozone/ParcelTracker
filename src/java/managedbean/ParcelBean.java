package managedbean;

import sellerUI.SellerCommandFactory;
import dto.OrderDTO;
import dto.ParcelDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import manager.DbManager;

@Named(value = "parcelBean")
@SessionScoped
public class ParcelBean implements Serializable
{
    private int id;
    private int recipientId;
    private int sellerId;
    
    private String name;
    private String type;
    private int weightGrams;
    
    private OrderDTO orderDetails = null;
    private ParcelDTO parcelDetails = null;
    private int totalOrders = 0;
    private int totalParcels = 0;
    
    @Inject
    UserBean userBean;
    
    public int getNextId() {

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
    
    public String deleteParcel(int parcelId) {
        
        try {
            Connection conn = DbManager.getConnection();

            // Delete orderparcels that contain this parcel due to FK constraint
            PreparedStatement stmt = conn.prepareStatement("" + 
                "DELETE FROM OrderParcels OP " + 
                "WHERE OP.parcelId = ? "
            );
            stmt.setInt(1, parcelId);
            stmt.executeUpdate();
            
            // Delete the parcel itself
            stmt = conn.prepareStatement(""
                + "DELETE FROM Parcels "
                + "WHERE id = ? "
            );
            stmt.setInt(1, parcelId);
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return "Seller_UI";
    }
    
    public String createParcel()
    {
        ParcelDTO newParcel = new ParcelDTO(
                                    getNextId(),
                                    name,
                                    type,
                                    weightGrams,
                                    userBean.findUserDetailsById(sellerId),
                                    "", /* will be now() on insert */
                                    "",
                                    0, /* times sold */
                                    0  /* quantityInOrder placeholder */
        );

        ParcelDTO insertedParcel 
                = (ParcelDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.CREATE_PARCEL,
                                newParcel)
                        .execute();

        parcelDetails = insertedParcel;

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
            
    public ArrayList<ParcelDTO> getParcelSummaries()
    {
        ArrayList<ParcelDTO> parcelSummaries
                = (ArrayList<ParcelDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.GET_PARCEL_SUMMARIES)
                        .execute();

        totalParcels = parcelSummaries.size();

        return parcelSummaries;
    }
    
    public ArrayList<OrderDTO> getOrderSummariesByUser(int UserID)
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) SellerCommandFactory
                        .createCommand(SellerCommandFactory.GET_ORDER_SUMMARIES_BY_USER,
                                UserID)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String fetchParcelDetails(int parcelID)
    {
        parcelDetails
                = (ParcelDTO) SellerCommandFactory
                        .createCommand(SellerCommandFactory.FIND_PARCEL_BY_ID,
                                parcelID)
                        .execute();

        return "viewParcel";
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
