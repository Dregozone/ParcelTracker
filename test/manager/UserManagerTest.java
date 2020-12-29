package manager;

import dto.UserDTO;
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
}
