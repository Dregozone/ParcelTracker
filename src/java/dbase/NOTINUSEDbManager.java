package dbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NOTINUSEDbManager
{
    public static Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        //return DriverManager.getConnection("jdbc:derby://localhost:1527/WMAD_demo", "user1", "user1");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/ParcelTracker", "acl", "123");
    }    
}
