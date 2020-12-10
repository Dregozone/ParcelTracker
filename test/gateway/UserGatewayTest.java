package gateway;

import dto.UserDTO;
import java.util.ArrayList;
import static junit.framework.Assert.*;
import org.junit.Test;

public class UserGatewayTest {
    
    public UserGatewayTest() {
    }

    /**
     * Test of find method, of class UserGateway.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        int UserID = 0;
        UserGateway instance = new UserGateway();
        UserDTO expResult = null;
        UserDTO result = instance.find(UserID);
        assertEquals(expResult, result);
    }

    /**
     * Test of findAllUsers method, of class UserGateway.
     */
    @Test
    public void testFindAllUsers() {
        System.out.println("findAllUsers");
        UserGateway instance = new UserGateway();
        ArrayList<UserDTO> expResult = null;
        ArrayList<UserDTO> result = instance.findAllUsers();
        assertEquals(expResult, result);
    }
}
