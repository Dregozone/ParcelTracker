package gateway;

import manager.DbManager;
import dto.UserDTO;
import dto.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TransactionGateway
{
    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }
    
    public boolean insertTransaction(TransactionDTO transaction)
    {
        boolean insertOK = false;
        
        try
        {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(""
                    + "INSERT INTO Transactions "
                    + "(id, orderid, name, addedby, dateAdded) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, transaction.getId());
            stmt.setInt(2, transaction.getOrderId());
            stmt.setString(3, transaction.getName());
            stmt.setInt(4, transaction.getAddedBy().getId());
            stmt.setDate(5, getDate() );

            stmt.executeUpdate();
            
            switch ( transaction.getName() ) {
                case "Picked up": 
                    // also update Orders.DriverID
                    try {
                        stmt = conn.prepareStatement(""
                                + "UPDATE Orders "
                                + "SET driverID = ? "
                                + "WHERE id = ? "
                        );

                        stmt.setInt(1, transaction.getAddedBy().getId());
                        stmt.setInt(2, transaction.getOrderId());

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
                        stmt.setInt(2, transaction.getOrderId());

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
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return insertOK;
    }
    
    public boolean deleteTransaction(TransactionDTO transaction)
    {   
        try {
            Connection conn = DbManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(""
                + "DELETE FROM Transactions "
                + "WHERE id = ? "
            );

            stmt.setInt(1, transaction.getId());
            stmt.executeUpdate();
            
            switch ( transaction.getName() ) {
                case "Picked up":
                    // Also change orders.driver back to id4 (None)
                    stmt = conn.prepareStatement(""
                        + "UPDATE Orders "
                        + "SET DriverID = 4 "
                        + "WHERE id = ? "
                    );

                    stmt.setInt(1, transaction.getOrderId());
                    stmt.executeUpdate();
                    
                    break;
                    
                case "Dropped off":
                    // Also change orders.isComplete=false, orders.datecompleted=NULL
                    stmt = conn.prepareStatement(""
                        + "UPDATE Orders "
                        + "SET IsComplete = false, DateCompleted = NULL "
                        + "WHERE id = ? "
                    );

                    stmt.setInt(1, transaction.getOrderId());
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
            
            return false;
        }
        
        return true;
    }
    
    public TransactionDTO find(int TransactionID)
    {
        TransactionDTO transactionDetails = null;
        
        try
        {            
            Connection conn = DbManager.getConnection();
            
            String sqlStr = "" + 
                    "SELECT " +
                    "    T.id AS tid, T.name AS tn, T.DATEADDED AS td, " +
                    "    U.id AS uid, U.FIRSTNAME AS uf, U.LASTNAME AS ul, U.USERNAME AS uu, U.HASHEDPASSWORD AS uh, U.DateAdded AS uda, U.DATEMODIFIED AS udm, U.ADDRESSLINEONE AS ua, U.TOWN AS ut, U.COUNTY AS uc, U.POSTCODE AS upc, U.email AS ue, U.PHONE AS up, U.ISACTIVE AS ui, R.name AS ur, " +
                    "    O.id AS oid, O.dateadded AS oda, O.ISCOMPLETE AS oi, O.DATECOMPLETED AS odc, " +
                    "    Rec.id AS rid, Rec.FIRSTNAME AS rf, Rec.LASTNAME AS rl, Rec.USERNAME AS ru, Rec.HASHEDPASSWORD AS rh, Rec.DateAdded AS rda, Rec.DATEMODIFIED AS rdm, Rec.ADDRESSLINEONE AS ra, Rec.TOWN AS rt, Rec.COUNTY AS rc, Rec.POSTCODE AS rp, Rec.email AS re, Rec.PHONE AS rp, Rec.ISACTIVE AS ri, RRec.name AS rr, " +
                    "    Dri.id AS did, Dri.FIRSTNAME AS df, Dri.LASTNAME AS dl, Dri.USERNAME AS du, Dri.HASHEDPASSWORD AS dh, Dri.DateAdded AS dda, Dri.DATEMODIFIED AS ddm, Dri.ADDRESSLINEONE AS da, Dri.TOWN AS dt, Dri.COUNTY AS dc, Dri.POSTCODE AS dp, Dri.email AS de, Dri.PHONE AS dp, Dri.ISACTIVE AS di, RDri.name AS dr, " +
                    "    Sel.id AS sid, Sel.FIRSTNAME AS sf, Sel.LASTNAME AS sl, Sel.USERNAME AS su, Sel.HASHEDPASSWORD AS sh, Sel.DateAdded AS sda, Sel.DATEMODIFIED AS sdm, Sel.ADDRESSLINEONE AS sa, Sel.TOWN AS st, Sel.COUNTY AS sc, Sel.POSTCODE AS sp, Sel.email AS se, Sel.PHONE AS sp, Sel.ISACTIVE AS si, RSel.name AS sr " +
                    "FROM TRANSACTIONS T " +
                    "    JOIN USERS U ON T.ADDEDBY = U.ID " +
                    "    JOIN ORDERS O ON T.ORDERID = O.ID " +
                    "    JOIN USERROLES UR ON U.id = UR.USERID " +
                    "    JOIN ROLES R ON UR.roleid = R.ID " +
                    "    JOIN USERS Rec ON O.RECIPIENTID = Rec.ID " +
                    "    JOIN USERROLES UR1 ON Rec.id = UR1.USERID " +
                    "    JOIN ROLES RRec ON UR1.roleid = RRec.ID " +
                    "    JOIN USERS Dri ON O.driverid = Dri.ID " +
                    "    JOIN USERROLES UR2 ON Dri.id = UR2.USERID " +
                    "    JOIN ROLES RDri ON UR2.roleid = RDri.ID " +
                    "    JOIN USERS Sel ON O.SELLERID = Sel.ID " +
                    "    JOIN USERROLES UR3 ON Sel.id = UR3.USERID " +
                    "    JOIN ROLES RSel ON UR3.roleid = RSel.ID " + 
                    "WHERE T.id = ? " +
            "";
            
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            
            stmt.setInt(1, TransactionID);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                transactionDetails = new TransactionDTO(
                        rs.getInt("tid"),
                        rs.getInt("oid"),
                        /*
                        new OrderDTO(
                                rs.getInt("oid"),
                                new UserDTO(rs.getInt("rid"), rs.getString("rfn"), rs.getString("rln"), rs.getString("ru"), rs.getString("rhp"), rs.getString("rda"), rs.getString("rdm"), rs.getString("ra"), rs.getString("rt"), rs.getString("rc"), rs.getString("rp"), rs.getString("re"), rs.getString("rp"), rs.getBoolean("ri"), rs.getString("rr")),
                                new UserDTO(rs.getInt("did"), rs.getString("dfn"), rs.getString("dln"), rs.getString("du"), rs.getString("dhp"), rs.getString("dda"), rs.getString("ddm"), rs.getString("da"), rs.getString("dt"), rs.getString("dc"), rs.getString("dp"), rs.getString("de"), rs.getString("dp"), rs.getBoolean("di"), rs.getString("dr")),
                                new UserDTO(rs.getInt("sid"), rs.getString("sfn"), rs.getString("sln"), rs.getString("su"), rs.getString("shp"), rs.getString("sda"), rs.getString("sdm"), rs.getString("sa"), rs.getString("st"), rs.getString("sc"), rs.getString("sp"), rs.getString("se"), rs.getString("sp"), rs.getBoolean("si"), rs.getString("sr")),
                                rs.getString("oda"),
                                rs.getBoolean("oi"),
                                rs.getString("odc")
                        ),
                        */
                        rs.getString("tn"),
                        //new UserDTO(rs.getInt("uid"), rs.getString("ufn"), rs.getString("uln"), rs.getString("uu"), rs.getString("uhp"), rs.getString("uda"), rs.getString("udm"), rs.getString("ua"), rs.getString("ut"), rs.getString("uc"), rs.getString("up"), rs.getString("ue"), rs.getString("up"), rs.getBoolean("ui"), rs.getString("ur")),
                        new UserDTO(rs.getInt("uid"), rs.getString("uf"), rs.getString("ul"), rs.getString("uu"), rs.getString("uh"), rs.getString("uda"), rs.getString("udm"), rs.getString("ua"), rs.getString("ut"), rs.getString("uc"), rs.getString("upc"), rs.getString("ue"), rs.getString("up"), rs.getBoolean("ui"), rs.getString("ur")),
                        rs.getString("td")
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
        
        return transactionDetails;
    }
    
    /** Find list of transactions by orderID
     * 
     * @param OrderID
     * @return 
     */
    public ArrayList<TransactionDTO> findOrderTransactions(int OrderID)
    {
        ArrayList<TransactionDTO> transactionSummaries = new ArrayList<>();
        try
        {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("" + 
                    "SELECT o.id AS oid, t.id AS tid, t.name AS tn, t.dateAdded as tda, u.id AS uid, u.firstname AS uf, u.lastname AS ul, u.username AS uu, u.hashedpassword AS uh, u.dateadded AS uda, u.datemodified AS udm, u.addresslineone AS ua, u.town AS ut, u.county AS uc, u.postcode AS up, u.email AS ue, u.phone AS up, u.isactive AS ui " +
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
                        new UserDTO(rs.getInt("uid"), rs.getString("uf"), rs.getString("ul"), rs.getString("uu"), rs.getString("uh"), rs.getString("uda"), rs.getString("udm"), rs.getString("ua"), rs.getString("ut"), rs.getString("uc"), rs.getString("up"), rs.getString("ue"), rs.getString("up"), rs.getBoolean("ui"), "Recipient"),
                        rs.getString("tda")
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
}
