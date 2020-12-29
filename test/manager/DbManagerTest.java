package manager;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

public class DbManagerTest {
    
    public DbManagerTest() {
    }

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("___ getConnection");
        
        String expResult = "org.apache.derby.client.net.NetConnection";
        Connection result = DbManager.getConnection();
        
        //System.out.println( result.getClass().getName() );
        
        assertEquals(expResult, result.getClass().getName() ); // This is a successful DB connection
    }
}
