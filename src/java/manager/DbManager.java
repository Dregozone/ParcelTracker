package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager
{
    /*
    public static Connection getConnectionSample() throws SQLException ////Will delete this method eventually
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
    }
    */
    
    public static Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        //return DriverManager.getConnection("jdbc:derby://localhost:1527/WMAD_demo", "user1", "user1");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/ParcelTracker", "acl", "123");
    }  
}
