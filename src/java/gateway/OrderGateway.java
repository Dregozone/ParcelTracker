package gateway;

import manager.DbManager;
import dto.OrderDTO;
import dto.UserDTO;
import dto.ParcelDTO;
import dto.MetricDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderGateway
{
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }
    
    public boolean insertOrder(OrderDTO order)
    {
        boolean insertOK = false;
        
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "INSERT INTO Orders "
                    + "(id, recipientid, driverid, sellerid, dateadded, iscomplete, datecompleted) "
                    + "values "
                    + "(?, ?, ?, ?, ?, false, null)"
            );
            
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getRecipient().getId());
            stmt.setInt(3, 4); // "None"
            stmt.setInt(4, order.getSeller().getId());
            stmt.setDate(5, getDate() );
            
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
    
    public boolean updateOrder(OrderDTO order)
    {        
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "UPDATE Orders "
                    + "SET recipientid = ?, sellerid = ? "
                    + "WHERE id = ? "
            );
            
            stmt.setInt(1, order.getRecipient().getId());
            stmt.setInt(2, order.getSeller().getId());
            stmt.setInt(3, order.getId());
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            
            return false;
        }
        
        return true;
    }
    
    public boolean deleteOrder(int orderId)
    {        
        try
        {
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
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            
            return false;
        }
        
        return true;
    }
    
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

    public ArrayList<OrderDTO> findRecipientOrders(int UserID)
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
                    "WHERE Recipient.ID = ? " + 
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
    
    public ArrayList<MetricDTO> findDriverMetrics()
    {
        ArrayList<MetricDTO> deliveryMetrics = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT " +
                    "    A1.username, " +
                    "    AVG(A1.daystocomplete) AS DaysToComplete, " +
                    "    MAX(A1.deliveryCount) AS DeliveryCount " +
                    "FROM " +
                    "(" +
                    "    SELECT " +
                    "        U.Username, " +
                    "        {fn TIMESTAMPDIFF( SQL_TSI_DAY, O.DATEADDED, O.DateCompleted)} AS DaysToComplete, " +
                    "        OrderCounts.DeliveryCount AS DeliveryCount " +
                    "    FROM  ORDERS O " +
                    "        JOIN USERS U ON O.driverid = U.id " +
                    "        JOIN (" +
                    "            SELECT " +
                    "                U.username AS username, " +
                    "                COUNT(*) AS DeliveryCount " +
                    "            FROM ORDERS O " +
                    "                JOIN USERS U ON O.DRIVERID = U.ID " +
                    "            GROUP BY U.USERNAME " +
                    "        ) OrderCounts ON U.USERNAME = OrderCounts.username " +
                    "    WHERE O.DATECOMPLETED IS NOT NULL AND U.Username <> 'None' " +
                    ") AS A1 " +
                    "GROUP BY A1.username " + 
            "");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                MetricDTO metric = new MetricDTO(
                        rs.getString("username"),
                        rs.getInt("DaysToComplete"),
                        rs.getInt("DeliveryCount")
                );
                
                deliveryMetrics.add(metric);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return deliveryMetrics;
    }
    
    public ArrayList<OrderDTO> findAllOrders()
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

    /** Find list of parcels by orderID
     * 
     * @param OrderID
     * @return 
     */
    public ArrayList<ParcelDTO> findOrderParcels(int OrderID)
    {
        ArrayList<ParcelDTO> parcelSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT P.id AS pid, OP.quantity, p.name AS pn, p.type AS pt, p.weightgrams AS pw, P.DATEADDED AS pda, P.datemodified AS pdm, Seller.* " + 
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
