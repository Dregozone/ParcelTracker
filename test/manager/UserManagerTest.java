package manager;

import dto.UserDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserManagerTest {
    
    public UserManagerTest() {
    }

    @Test
    public void testFindUser() {
        System.out.println("__ findUser");
        
        int UserID = 3;
        
        UserManager instance = new UserManager();
        
        String expResult = "seller";
        UserDTO result = instance.findUser(UserID);
        
        assertEquals( expResult, result.getUsername() );
    }

    @Test
    public void testFindUserByUsername() {
        System.out.println("__ findUserByUsername");
        
        String username = "seller";
        
        UserManager instance = new UserManager();
        
        String expResult = "seller";
        UserDTO result = instance.findUserByUsername(username);
        
        assertEquals( expResult, result.getUsername() );
    }

    @Test
    public void testFindUserRole() {
        System.out.println("__ findUserRole");
        
        String username = "seller";
        
        UserManager instance = new UserManager();
        
        String expResult = "Seller";
        String result = instance.findUserRole(username);
        
        assertEquals(expResult, result);
    }
    
    /*
    @Test
    public void testViewAllUsers() {
        System.out.println("viewAllUsers");
        UserManager instance = new UserManager();
        ArrayList<UserDTO> expResult = null;
        ArrayList<UserDTO> result = instance.viewAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        UserDTO user = null;
        UserManager instance = new UserManager();
        boolean expResult = false;
        boolean result = instance.createUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
