package gateway;

import manager.DbManager;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.UserDTO;
import dto.ParcelDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ParcelGateway
{
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }
    
    public boolean createParcel(ParcelDTO parcel)
    {
        boolean insertOK = false;
        
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "INSERT INTO Parcels "
                    + "(id, sellerId, name, type, weightGrams, dateAdded, dateModified, timesSold) "
                    + "values "
                    + "(?, ?, ?, ?, ?, ?, ?, 0)"
            );
            
            stmt.setInt(1, parcel.getId());
            stmt.setInt(2, parcel.getSeller().getId());
            stmt.setString(3, parcel.getName());
            stmt.setString(4, parcel.getType());
            stmt.setInt(5, parcel.getWeightGrams());
            stmt.setDate(6, getDate() );
            stmt.setDate(7, getDate() );
            
            int rows = stmt.executeUpdate();
            
            insertOK = rows == 1;

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return insertOK;
    }
    
    public ParcelDTO find(int ParcelID)
    {
        ParcelDTO parcelDetails = null;
        
        try
        {            
            Connection conn = DbManager.getConnection();
            
            String sqlStr = "" + 
                    "SELECT P.*, Seller.ID AS sid, Seller.FIRSTNAME AS sf, Seller.lastName AS sl, Seller.Username AS su, Seller.HASHEDPASSWORD AS sh, Seller.DATEADDED AS sda, Seller.DATEMODIFIED AS sdm, Seller.ADDRESSLINEONE AS sa, Seller.TOWN AS st, Seller.County AS sc, Seller.Postcode AS sp, Seller.EMAIL AS se, Seller.phone AS sp, Seller.ISACTIVE AS si, Roles1.name AS sr " +
                    "FROM PARCELS P " +
                    "JOIN Users Seller ON P.SELLERID = Seller.ID " +
                    "JOIN UserRoles AS UserRoles1 ON Seller.id = UserRoles1.USERID " +
                    "JOIN Roles AS Roles1 ON UserRoles1.ROLEID = Roles1.ID " +
                    "WHERE P.id = ?"
            ;
            
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            
            stmt.setInt(1, ParcelID);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                parcelDetails = new ParcelDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("weightGrams"),
                        new UserDTO(rs.getInt("sid"), rs.getString("sf"), rs.getString("sl"), rs.getString("su"), rs.getString("sh"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                        rs.getString("dateAdded"),
                        rs.getString("dateModified"),
                        rs.getInt("timesSold"),
                        0 /* quantityInOrder placeholder */
                );
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return parcelDetails;
    }
    
    public ArrayList<ParcelDTO> findAllParcelSummaries()
    {
        ArrayList<ParcelDTO> parcelSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT P.*, Seller.ID AS sid, Seller.FIRSTNAME AS sf, Seller.lastName AS sl, Seller.Username AS su, Seller.HASHEDPASSWORD AS sh, Seller.DATEADDED AS sda, Seller.DATEMODIFIED AS sdm, Seller.ADDRESSLINEONE AS sa, Seller.TOWN AS st, Seller.County AS sc, Seller.Postcode AS sp, Seller.EMAIL AS se, Seller.phone AS sp, Seller.ISACTIVE AS si, Roles1.name AS sr " +
                    "FROM PARCELS P " +
                    "JOIN Users Seller ON P.SELLERID = Seller.ID " + 
                    "JOIN UserRoles AS UserRoles1 ON Seller.id = UserRoles1.USERID " +
                    "JOIN Roles AS Roles1 ON UserRoles1.ROLEID = Roles1.ID " +
                    "");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                ParcelDTO parcel = new ParcelDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("weightGrams"),
                        new UserDTO(rs.getInt("sid"), rs.getString("sf"), rs.getString("sl"), rs.getString("su"), rs.getString("sh"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                        rs.getString("dateAdded"),
                        rs.getString("dateModified"),
                        rs.getInt("timesSold"),
                        0 /* quantityInOrder placeholder */
                );
                
                parcelSummaries.add(parcel);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return parcelSummaries;
    }

    /** Find list of parcels by orderID
     * 
     * @param ParcelID
     * @return 
     */
    public ArrayList<ParcelDTO> findAllSummariesByOrder(int OrderID)
    {
        ArrayList<ParcelDTO> parcelSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT P.id AS pid, OP.quantity, p.name AS pn, p.type AS pt, p.weightgrams AS pw, P.DATEADDED AS pda, P.datemodified AS pdm, P.timessold AS pts, Seller.* " + 
                    "FROM ORDERS AS O " +
                    "JOIN OrderParcels OP ON O.id = OP.ORDERID " +
                    "JOIN Parcels P ON OP.PARCELID = P.ID " +
                    "JOIN Users Seller ON P.SELLERID = Seller.ID " +
                    "WHERE o.ID = ? " + 
            "");
            
            stmt.setInt(1, OrderID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                ParcelDTO parcel = new ParcelDTO(
                        rs.getInt("pid"),
                        rs.getString("pn"),
                        rs.getString("pt"),
                        rs.getInt("pw"),
                        new UserDTO(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("hashedPassword"), rs.getString("dateAdded"), rs.getString("dateModified"), rs.getString("addressLineOne"), rs.getString("town"), rs.getString("county"), rs.getString("postcode"), rs.getString("email"), rs.getString("phone"), rs.getBoolean("isActive"), "Recipient"),
                        rs.getString("pda"),
                        rs.getString("pdm"),
                        rs.getInt("pts"),
                        rs.getInt("quantity")
                );
                
                parcelSummaries.add(parcel);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return parcelSummaries;
    }
}
