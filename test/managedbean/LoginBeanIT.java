/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class LoginBeanIT {
    
    public LoginBeanIT() {
    }

    /**
     * Test of checkCredentials method, of class LoginBean.
     */
    @Test
    public void testCheckCredentials() {
        System.out.println("checkCredentials");
        LoginBean instance = new LoginBean();
        String expResult = "";
        String result = instance.checkCredentials();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userPage method, of class LoginBean.
     */
    @Test
    public void testUserPage() {
        System.out.println("userPage");
        LoginBean instance = new LoginBean();
        String expResult = "";
        String result = instance.userPage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class LoginBean.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        LoginBean instance = new LoginBean();
        String expResult = "";
        String result = instance.logout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of credentialsAreOK method, of class LoginBean.
     */
    @Test
    public void testCredentialsAreOK() {
        System.out.println("credentialsAreOK");
        LoginBean instance = new LoginBean();
        boolean expResult = false;
        boolean result = instance.credentialsAreOK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class LoginBean.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        LoginBean instance = new LoginBean();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class LoginBean.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginBean instance = new LoginBean();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginBean.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginBean instance = new LoginBean();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class LoginBean.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        LoginBean instance = new LoginBean();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class LoginBean.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        LoginBean instance = new LoginBean();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
