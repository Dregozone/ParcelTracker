package managedbean;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginBeanTest {
    
    public LoginBeanTest() {
    }

    @Test
    public void testUserPage() {
        System.out.println("___ userPage");
        
        LoginBean instance = new LoginBean();
        
        // Log in as seller
        instance.setUsername("seller");
        instance.setPassword("123");
        
        // Find calculated user page value
        String expResult = "Seller_UI"; // Initial user "seller" has a known userPage of "Seller_UI"
        String result = instance.userPage();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testLogout() {
        System.out.println("___ logout");
        
        LoginBean instance = new LoginBean();
        
        // Log in as seller
        instance.setUsername("seller");
        instance.setPassword("123");
        
        String expResult = "";
        
        try {
            instance.logout();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        String result = instance.getUsername();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckCredentialsAndFindIfCredentialsAreOKValid() { // Load values, check if valid against DB, then pull bool value from class
        System.out.println("___ Credentials (valid)");
        
        LoginBean instance = new LoginBean();
        
        // Load credentials for checking
        instance.setUsername("seller");
        instance.setPassword("123");
        
        // Perform check of provided credentials
        try {
            instance.checkCredentials();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Get valid/invalid credentials value as boolean from class
        boolean result = instance.credentialsAreOK();
        
        assertTrue( result );
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("___ Id");
        
        LoginBean instance = new LoginBean();
        
        int expResult = 0;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetUsername() {
        System.out.println("___ Username");
        
        LoginBean instance = new LoginBean();
        
        String expResult = "TestUser";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetPassword() {
        System.out.println("___ Password");
        
        LoginBean instance = new LoginBean();
        
        String unhashedPassword = "pass";
        instance.setPassword(unhashedPassword);
        
        String hashedPassword = "10/w7o2juYBrGMh32/KbveULW9jk2tejpyUAD+uC6PE="; // This is precalculated as the hash of "pass"
        String result = instance.getPassword();
        
        assertEquals(hashedPassword, result);
    }
}
