/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.UserDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class UserManagerIT {
    
    public UserManagerIT() {
    }

    /**
     * Test of findUser method, of class UserManager.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser");
        int UserID = 0;
        UserManager instance = new UserManager();
        UserDTO expResult = null;
        UserDTO result = instance.findUser(UserID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByUsername method, of class UserManager.
     */
    @Test
    public void testFindUserByUsername() {
        System.out.println("findUserByUsername");
        String username = "";
        UserManager instance = new UserManager();
        UserDTO expResult = null;
        UserDTO result = instance.findUserByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserRole method, of class UserManager.
     */
    @Test
    public void testFindUserRole() {
        System.out.println("findUserRole");
        String username = "";
        UserManager instance = new UserManager();
        String expResult = "";
        String result = instance.findUserRole(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewAllUsers method, of class UserManager.
     */
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

    /**
     * Test of createUser method, of class UserManager.
     */
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
    
}
