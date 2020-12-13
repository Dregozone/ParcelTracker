/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class RegisterBeanTest {
    
    public RegisterBeanTest() {
    }

    /**
     * Test of getDate method, of class RegisterBean.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        RegisterBean instance = new RegisterBean();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextId method, of class RegisterBean.
     */
    @Test
    public void testGetNextId() {
        System.out.println("getNextId");
        RegisterBean instance = new RegisterBean();
        int expResult = 0;
        int result = instance.getNextId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class RegisterBean.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.register();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class RegisterBean.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class RegisterBean.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword1 method, of class RegisterBean.
     */
    @Test
    public void testGetPassword1() {
        System.out.println("getPassword1");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getPassword1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword2 method, of class RegisterBean.
     */
    @Test
    public void testGetPassword2() {
        System.out.println("getPassword2");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getPassword2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class RegisterBean.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class RegisterBean.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String name = "";
        RegisterBean instance = new RegisterBean();
        instance.setFirstName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class RegisterBean.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String name = "";
        RegisterBean instance = new RegisterBean();
        instance.setLastName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class RegisterBean.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        RegisterBean instance = new RegisterBean();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword1 method, of class RegisterBean.
     */
    @Test
    public void testSetPassword1() {
        System.out.println("setPassword1");
        String password1 = "";
        RegisterBean instance = new RegisterBean();
        instance.setPassword1(password1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword2 method, of class RegisterBean.
     */
    @Test
    public void testSetPassword2() {
        System.out.println("setPassword2");
        String password2 = "";
        RegisterBean instance = new RegisterBean();
        instance.setPassword2(password2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressLineOne method, of class RegisterBean.
     */
    @Test
    public void testGetAddressLineOne() {
        System.out.println("getAddressLineOne");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getAddressLineOne();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddressLineOne method, of class RegisterBean.
     */
    @Test
    public void testSetAddressLineOne() {
        System.out.println("setAddressLineOne");
        String addressLineOne = "";
        RegisterBean instance = new RegisterBean();
        instance.setAddressLineOne(addressLineOne);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTown method, of class RegisterBean.
     */
    @Test
    public void testGetTown() {
        System.out.println("getTown");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getTown();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTown method, of class RegisterBean.
     */
    @Test
    public void testSetTown() {
        System.out.println("setTown");
        String town = "";
        RegisterBean instance = new RegisterBean();
        instance.setTown(town);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCounty method, of class RegisterBean.
     */
    @Test
    public void testGetCounty() {
        System.out.println("getCounty");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getCounty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCounty method, of class RegisterBean.
     */
    @Test
    public void testSetCounty() {
        System.out.println("setCounty");
        String county = "";
        RegisterBean instance = new RegisterBean();
        instance.setCounty(county);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostcode method, of class RegisterBean.
     */
    @Test
    public void testGetPostcode() {
        System.out.println("getPostcode");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getPostcode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPostcode method, of class RegisterBean.
     */
    @Test
    public void testSetPostcode() {
        System.out.println("setPostcode");
        String postcode = "";
        RegisterBean instance = new RegisterBean();
        instance.setPostcode(postcode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class RegisterBean.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class RegisterBean.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        RegisterBean instance = new RegisterBean();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class RegisterBean.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        RegisterBean instance = new RegisterBean();
        String expResult = "";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhone method, of class RegisterBean.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "";
        RegisterBean instance = new RegisterBean();
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
