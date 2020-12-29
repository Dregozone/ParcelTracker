package managedbean;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterBeanTest {
    
    public RegisterBeanTest() {
    }

    @Test
    public void testGetDate() { // getDate is now()
        System.out.println("___ getDate");
        
        RegisterBean instance = new RegisterBean();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNextId() { // getNextId returns a positive integer
        System.out.println("___ getNextId");
        
        RegisterBean instance = new RegisterBean();
        int expResult = 0;
        int result = instance.getNextId();
        
        assertTrue(result >= expResult);
    }

    /* Test:T1 */
    @Test
    public void testRegisterValid() { // Check that the next user ID increases after calling register()
        System.out.println(" T1 - testRegisterValid"); /* register (valid values) */
        
        RegisterBean instance = new RegisterBean();
        LoginBean loginInstance = new LoginBean();
        
        // Find next user ID value
        int CurrentNextUserID = instance.getNextId();
        
        // Load values to use with register
        instance.setFirstName("Anders");
        instance.setLastName("L");
        instance.setUsername("TestUser");
        instance.setPassword1("pass");
        instance.setPassword2("pass");
        instance.setAddressLineOne("123 Road Name");
        instance.setTown("Basingstoke");
        instance.setCounty("Hants");
        instance.setPostcode("RG112AA");
        instance.setEmail("a@b.com");
        instance.setPhone("01234567890");
        
        // Run registration
        try {
            instance.register();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Find new next user ID value
        int NewNextUserID = instance.getNextId();
        
        // Log in as new user
        loginInstance.setUsername("seller");
        loginInstance.setPassword("123");
        
        //assertNotNull(returned);
        assertTrue(NewNextUserID > CurrentNextUserID);
    }
    
    /* Test:T2 */
    @Test
    public void testRegisterInvalid() { // Check that the next user ID increases after calling register()
        System.out.println(" T2 - testRegisterInvalid"); /* register (invalid values) */
        
        RegisterBean instance = new RegisterBean();
        
        // Find next user ID value
        int CurrentNextUserID = instance.getNextId();
        
        // Load values to use with register
        instance.setFirstName("Anders");
        instance.setLastName("L");
        instance.setUsername("TestUser");
        instance.setPassword1("pass");
        instance.setPassword2("wrong");
        instance.setAddressLineOne("123 Road Name");
        instance.setTown("Basingstoke");
        instance.setCounty("Hants");
        instance.setPostcode("RG112AA");
        instance.setEmail("a@b.com");
        instance.setPhone("01234567890");
        
        // Run registration
        try {
            instance.register();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Find new next user ID value
        int NewNextUserID = instance.getNextId();
        
        // Check no record was added to DB by ensuring the get next user ID value is the same as before the register call
        assertTrue(NewNextUserID == CurrentNextUserID);
    }

    @Test
    public void testSetAndGetFirstName() { // Can set and get first name within bean
        System.out.println("___ FirstName");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "Anders";
        instance.setFirstName(expResult);        
        String result = instance.getFirstName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetLastName() { // Can set and get last name within bean
        System.out.println("___ LastName");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "Learmonth";
        instance.setLastName(expResult);
        String result = instance.getLastName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetPassword1() {
        System.out.println("___ Password1");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "pass1";
        instance.setPassword1(expResult);
        String result = instance.getPassword1();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetPassword2() {
        System.out.println("___ Password2");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "pass2";
        instance.setPassword2(expResult);
        String result = instance.getPassword2();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetUsername() {
        System.out.println("___ Username");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "Anders1";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetAddressLineOne() {
        System.out.println("___ AddressLineOne");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "123 Road Name";
        instance.setAddressLineOne(expResult);
        String result = instance.getAddressLineOne();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTown() {
        System.out.println("___ Town");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "Basingstoke";
        instance.setTown(expResult);
        String result = instance.getTown();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetCounty() {
        System.out.println("___ County");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "Hants";
        instance.setCounty(expResult);
        String result = instance.getCounty();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetPostcode() {
        System.out.println("___ Postcode");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "RG112AA";
        instance.setPostcode(expResult);
        String result = instance.getPostcode();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetEmail() {
        System.out.println("___ Email");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "a@b.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetPhone() {
        System.out.println("___ Phone");
        
        RegisterBean instance = new RegisterBean();
        
        String expResult = "01234567890";
        instance.setPhone(expResult); 
        String result = instance.getPhone();
        
        assertEquals(expResult, result);
    }    
}
