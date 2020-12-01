package gateway;

import manager.DbManager;
import dto.OrderDTO;
import dto.UserDTO;
import dto.ParcelDTO;
import dto.CustomerDTO;
import dto.TransactionDTO;
import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionGateway
{
    public OrderDTO find(int OrderID)
    {
        OrderDTO orderDetails = null;
        
        try
        {            
            Connection conn = DbManager.getConnection();
            
            String sqlStr = "" + 
                    "SELECT " +
                    "    ORDERS.*, " +
                    "    Recipient.id AS rid, " +
                    "    Recipient.firstName AS rfn, " +
                    "    Recipient.lastName AS rln, " +
                    "    Recipient.username AS ru, " +
                    "    Recipient.hashedPassword AS rhp, " +
                    "    Recipient.dateAdded AS rda, " +
                    "    Recipient.dateModified AS rdm, " +
                    "    Recipient.addressLineOne AS ra, " +
                    "    Recipient.town AS rt, " +
                    "    Recipient.county AS rc, " +
                    "    Recipient.postcode AS rp, " +
                    "    Recipient.email AS re, " +
                    "    Recipient.phone AS rp, " +
                    "    Recipient.isActive AS ri, " +
                    "    Roles1.name AS rr," +
                    "    Driver.id AS did,  " +
                    "    Driver.firstName AS dfn, " +
                    "    Driver.lastName AS dln, " +
                    "    Driver.username AS du, " +
                    "    Driver.hashedPassword AS dhp, " +
                    "    Driver.dateAdded AS dda, " +
                    "    Driver.dateModified AS ddm, " +
                    "    Driver.addressLineOne AS da, " +
                    "    Driver.town AS dt, " +
                    "    Driver.county AS dc, " +
                    "    Driver.postcode AS dp, " +
                    "    Driver.email AS de, " +
                    "    Driver.phone AS dp, " +
                    "    Driver.isActive AS di, " +
                    "    Roles2.name AS dr," +
                    "    Seller.id AS sid, " +
                    "    Seller.firstName AS sfn, " +
                    "    Seller.lastName AS sln, " +
                    "    Seller.username AS su, " +
                    "    Seller.hashedPassword AS shp, " +
                    "    Seller.dateAdded AS sda, " +
                    "    Seller.dateModified AS sdm, " +
                    "    Seller.addressLineOne AS sa, " +
                    "    Seller.town AS st, " +
                    "    Seller.county AS sc, " +
                    "    Seller.postcode AS sp, " +
                    "    Seller.email AS se, " +
                    "    Seller.phone AS sp, " +
                    "    Seller.isActive AS si, " +
                    "    Roles3.name AS sr " +
                    "FROM ORDERS " +
                    "JOIN Users Recipient ON Orders.RECIPIENTID = Recipient.id " +
                    "JOIN UserRoles AS UserRoles1 ON Recipient.id = UserRoles1.USERID " +
                    "JOIN Roles AS Roles1 ON UserRoles1.ROLEID = Roles1.ID " +
                    "JOIN Users Driver ON Orders.driverid = Driver.id " +
                    "JOIN UserRoles AS UserRoles2 ON Driver.id = UserRoles2.USERID " +
                    "JOIN Roles AS Roles2 ON UserRoles2.ROLEID = Roles2.ID " +
                    "JOIN Users Seller ON Orders.sellerid = Seller.id " +
                    "JOIN UserRoles AS UserRoles3 ON Seller.id = UserRoles3.USERID " +
                    "JOIN Roles AS Roles3 ON UserRoles3.ROLEID = Roles3.ID " +
                    "WHERE Orders.id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            
            stmt.setInt(1, OrderID);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                orderDetails = new OrderDTO(
                        rs.getInt("id"),
                        new UserDTO(rs.getInt("rid"), rs.getString("rfn"), rs.getString("rln"), rs.getString("ru"), rs.getString("rhp"), rs.getString("rda"), rs.getString("rdm"), rs.getString("ra"), rs.getString("rt"), rs.getString("rc"), rs.getString("rp"), rs.getString("re"), rs.getString("rp"), rs.getBoolean("ri"), rs.getString("rr")),
                        new UserDTO(rs.getInt("did"), rs.getString("dfn"), rs.getString("dln"), rs.getString("du"), rs.getString("dhp"), rs.getString("dda"), rs.getString("ddm"), rs.getString("da"), rs.getString("dt"), rs.getString("dc"), rs.getString("dp"), rs.getString("de"), rs.getString("dp"), rs.getBoolean("di"), rs.getString("dr")),
                        new UserDTO(rs.getInt("sid"), rs.getString("sfn"), rs.getString("sln"), rs.getString("su"), rs.getString("shp"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                        rs.getString("dateAdded"),
                        rs.getBoolean("isComplete"),
                        rs.getString("dateCompleted")
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
        
        return orderDetails;
    }

    /*
    public ArrayList<TransactionDTO> findAllTransactionSummariesByUser(int UserID)
    {
        ArrayList<TransactionDTO> transactionSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT " +
                    "    ORDERS.*, " +
                    "    Recipient.id AS rid, " +
                    "    Recipient.firstName AS rfn, " +
                    "    Recipient.lastName AS rln, " +
                    "    Recipient.username AS ru, " +
                    "    Recipient.hashedPassword AS rhp, " +
                    "    Recipient.dateAdded AS rda, " +
                    "    Recipient.dateModified AS rdm, " +
                    "    Recipient.addressLineOne AS ra, " +
                    "    Recipient.town AS rt, " +
                    "    Recipient.county AS rc, " +
                    "    Recipient.postcode AS rp, " +
                    "    Recipient.email AS re, " +
                    "    Recipient.phone AS rp, " +
                    "    Recipient.isActive AS ri, " +
                    "    Roles1.name AS rr," +
                    "    Driver.id AS did,  " +
                    "    Driver.firstName AS dfn, " +
                    "    Driver.lastName AS dln, " +
                    "    Driver.username AS du, " +
                    "    Driver.hashedPassword AS dhp, " +
                    "    Driver.dateAdded AS dda, " +
                    "    Driver.dateModified AS ddm, " +
                    "    Driver.addressLineOne AS da, " +
                    "    Driver.town AS dt, " +
                    "    Driver.county AS dc, " +
                    "    Driver.postcode AS dp, " +
                    "    Driver.email AS de, " +
                    "    Driver.phone AS dp, " +
                    "    Driver.isActive AS di, " +
                    "    Roles2.name AS dr," +
                    "    Seller.id AS sid, " +
                    "    Seller.firstName AS sfn, " +
                    "    Seller.lastName AS sln, " +
                    "    Seller.username AS su, " +
                    "    Seller.hashedPassword AS shp, " +
                    "    Seller.dateAdded AS sda, " +
                    "    Seller.dateModified AS sdm, " +
                    "    Seller.addressLineOne AS sa, " +
                    "    Seller.town AS st, " +
                    "    Seller.county AS sc, " +
                    "    Seller.postcode AS sp, " +
                    "    Seller.email AS se, " +
                    "    Seller.phone AS sp, " +
                    "    Seller.isActive AS si, " +
                    "    Roles3.name AS sr " +
                    "FROM ORDERS " +
                    "JOIN Users Recipient ON Orders.RECIPIENTID = Recipient.id " +
                    "JOIN UserRoles AS UserRoles1 ON Recipient.id = UserRoles1.USERID " +
                    "JOIN Roles AS Roles1 ON UserRoles1.ROLEID = Roles1.ID " +
                    "JOIN Users Driver ON Orders.driverid = Driver.id " +
                    "JOIN UserRoles AS UserRoles2 ON Driver.id = UserRoles2.USERID " +
                    "JOIN Roles AS Roles2 ON UserRoles2.ROLEID = Roles2.ID " +
                    "JOIN Users Seller ON Orders.sellerid = Seller.id " +
                    "JOIN UserRoles AS UserRoles3 ON Seller.id = UserRoles3.USERID " +
                    "JOIN Roles AS Roles3 ON UserRoles3.ROLEID = Roles3.ID " + 
                    "WHERE Recipient.ID = ? AND ORDERS.isComplete = false" + 
            "");
            
            stmt.setInt(1, UserID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                OrderDTO order = new OrderDTO(
                        rs.getInt("id"),
                        new UserDTO(rs.getInt("rid"), rs.getString("rfn"), rs.getString("rln"), rs.getString("ru"), rs.getString("rhp"), rs.getString("rda"), rs.getString("rdm"), rs.getString("ra"), rs.getString("rt"), rs.getString("rc"), rs.getString("rp"), rs.getString("re"), rs.getString("rp"), rs.getBoolean("ri"), rs.getString("rr")),
                        new UserDTO(rs.getInt("did"), rs.getString("dfn"), rs.getString("dln"), rs.getString("du"), rs.getString("dhp"), rs.getString("dda"), rs.getString("ddm"), rs.getString("da"), rs.getString("dt"), rs.getString("dc"), rs.getString("dp"), rs.getString("de"), rs.getString("dp"), rs.getBoolean("di"), rs.getString("dr")),
                        new UserDTO(rs.getInt("sid"), rs.getString("sfn"), rs.getString("sln"), rs.getString("su"), rs.getString("shp"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                        rs.getString("dateAdded"),
                        rs.getBoolean("isComplete"),
                        rs.getString("dateCompleted")
                );
                
                orderSummaries.add(order);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return orderSummaries;
    }
    */
    
    public ArrayList<OrderDTO> findAllSummaries()
    {
        ArrayList<OrderDTO> orderSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT " +
                    "    ORDERS.*, " +
                    "    Recipient.id AS rid, " +
                    "    Recipient.firstName AS rfn, " +
                    "    Recipient.lastName AS rln, " +
                    "    Recipient.username AS ru, " +
                    "    Recipient.hashedPassword AS rhp, " +
                    "    Recipient.dateAdded AS rda, " +
                    "    Recipient.dateModified AS rdm, " +
                    "    Recipient.addressLineOne AS ra, " +
                    "    Recipient.town AS rt, " +
                    "    Recipient.county AS rc, " +
                    "    Recipient.postcode AS rp, " +
                    "    Recipient.email AS re, " +
                    "    Recipient.phone AS rp, " +
                    "    Recipient.isActive AS ri, " +
                    "    Roles1.name AS rr," +
                    "    Driver.id AS did,  " +
                    "    Driver.firstName AS dfn, " +
                    "    Driver.lastName AS dln, " +
                    "    Driver.username AS du, " +
                    "    Driver.hashedPassword AS dhp, " +
                    "    Driver.dateAdded AS dda, " +
                    "    Driver.dateModified AS ddm, " +
                    "    Driver.addressLineOne AS da, " +
                    "    Driver.town AS dt, " +
                    "    Driver.county AS dc, " +
                    "    Driver.postcode AS dp, " +
                    "    Driver.email AS de, " +
                    "    Driver.phone AS dp, " +
                    "    Driver.isActive AS di, " +
                    "    Roles2.name AS dr," +
                    "    Seller.id AS sid, " +
                    "    Seller.firstName AS sfn, " +
                    "    Seller.lastName AS sln, " +
                    "    Seller.username AS su, " +
                    "    Seller.hashedPassword AS shp, " +
                    "    Seller.dateAdded AS sda, " +
                    "    Seller.dateModified AS sdm, " +
                    "    Seller.addressLineOne AS sa, " +
                    "    Seller.town AS st, " +
                    "    Seller.county AS sc, " +
                    "    Seller.postcode AS sp, " +
                    "    Seller.email AS se, " +
                    "    Seller.phone AS sp, " +
                    "    Seller.isActive AS si, " +
                    "    Roles3.name AS sr " +
                    "FROM ORDERS " +
                    "JOIN Users Recipient ON Orders.RECIPIENTID = Recipient.id " +
                    "JOIN UserRoles AS UserRoles1 ON Recipient.id = UserRoles1.USERID " +
                    "JOIN Roles AS Roles1 ON UserRoles1.ROLEID = Roles1.ID " +
                    "JOIN Users Driver ON Orders.driverid = Driver.id " +
                    "JOIN UserRoles AS UserRoles2 ON Driver.id = UserRoles2.USERID " +
                    "JOIN Roles AS Roles2 ON UserRoles2.ROLEID = Roles2.ID " +
                    "JOIN Users Seller ON Orders.sellerid = Seller.id " +
                    "JOIN UserRoles AS UserRoles3 ON Seller.id = UserRoles3.USERID " +
                    "JOIN Roles AS Roles3 ON UserRoles3.ROLEID = Roles3.ID " + 
            "");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                OrderDTO order = new OrderDTO(
                        rs.getInt("id"),
                        new UserDTO(rs.getInt("rid"), rs.getString("rfn"), rs.getString("rln"), rs.getString("ru"), rs.getString("rhp"), rs.getString("rda"), rs.getString("rdm"), rs.getString("ra"), rs.getString("rt"), rs.getString("rc"), rs.getString("rp"), rs.getString("re"), rs.getString("rp"), rs.getBoolean("ri"), rs.getString("rr")),
                        new UserDTO(rs.getInt("did"), rs.getString("dfn"), rs.getString("dln"), rs.getString("du"), rs.getString("dhp"), rs.getString("dda"), rs.getString("ddm"), rs.getString("da"), rs.getString("dt"), rs.getString("dc"), rs.getString("dp"), rs.getString("de"), rs.getString("dp"), rs.getBoolean("di"), rs.getString("dr")),
                        new UserDTO(rs.getInt("sid"), rs.getString("sfn"), rs.getString("sln"), rs.getString("su"), rs.getString("shp"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                        rs.getString("dateAdded"),
                        rs.getBoolean("isComplete"),
                        rs.getString("dateCompleted")
                );
                
                orderSummaries.add(order);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return orderSummaries;
    }

    /** Find list of transactions by orderID
     * 
     * @param OrderID
     * @return 
     */
    public ArrayList<TransactionDTO> findAllTransactionSummariesByOrder(int OrderID)
    {
        ArrayList<TransactionDTO> transactionSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT o.id AS oid, t.id AS tid, t.name AS tn, u.id AS uid, u.firstname AS uf, u.lastname AS ul, u.username AS uu, u.hashedpassword AS uh, u.dateadded AS uda, u.datemodified AS udm, u.addresslineone AS ua, u.town AS ut, u.county AS uc, u.postcode AS up, u.email AS ue, u.phone AS up, u.isactive AS ui " +
                    "FROM TRANSACTIONS T " +
                    "JOIN Users U ON T.ADDEDBY = U.ID " +
                    "JOIN ORDERS O ON T.orderid = O.ID " +
                    " WHERE T.OrderID = ?" +
            "");
            
            stmt.setInt(1, OrderID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                TransactionDTO transaction = new TransactionDTO(
                        rs.getInt("tid"),
                        rs.getInt("oid"), 
                        rs.getString("tn"),
                        new UserDTO(rs.getInt("uid"), rs.getString("uf"), rs.getString("ul"), rs.getString("uu"), rs.getString("uh"), rs.getString("uda"), rs.getString("udm"), rs.getString("ua"), rs.getString("ut"), rs.getString("uc"), rs.getString("up"), rs.getString("ue"), rs.getString("up"), rs.getBoolean("ui"), "Recipient")
                );
                
                transactionSummaries.add(transaction);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return transactionSummaries;
    }
    
    public boolean insert(CustomerDTO cust)
    {
        boolean insertOK = false;
        try
        {
            Connection conn = DbManager.getConnectionSample();
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customer (customer_id, discount_code, zip, name, addressline1, addressline2, city, state) values (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, cust.getId());
            stmt.setString(2, cust.getDiscount().getCode());
            stmt.setString(3, cust.getZipCode());
            stmt.setString(4, cust.getName());
            stmt.setString(5, cust.getAddressLine1());
            stmt.setString(6, cust.getAddressLine2());
            stmt.setString(7, cust.getCity());
            stmt.setString(8, cust.getState());
            
            int rows = stmt.executeUpdate();
            
            insertOK = rows == 1;

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return insertOK;
    }

    public boolean delete(int CustID)
    {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean update(CustomerDTO cust)
    {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }
}
