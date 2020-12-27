package managedbean;

import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecipientBeanTest {
    
    public RecipientBeanTest() {
    }

    @Test
    public void testFindRoleByUser() {
        System.out.println("__ findRoleByUser");
        
        int userID = 1;
        
        RecipientBean instance = new RecipientBean();
        
        String expResult = "Recipient";
        String result = instance.findRoleByUser(userID);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testViewOrderTransactions() {
        System.out.println("__ viewOrderTransactions");
        
        RecipientBean instance = new RecipientBean();
        SellerBean sellerInstance = new SellerBean();
        DriverBean driverInstance = new DriverBean();
        
        // Prep order info for this recipient
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        
        // Create orders
        int order1Id = sellerInstance.getNextOrderId();
        sellerInstance.createOrder();
        
        // Add transactions
        int transaction1Id = driverInstance.getNextTransactionId();
        driverInstance.addTransaction(order1Id, "Picked up", 2); // Use driver to add transaction
        
        int transaction2Id = driverInstance.getNextTransactionId();
        driverInstance.addTransaction(order1Id, "Dropped off", 2); // Use driver to add transaction
        
        // Find transactions against this order
        ArrayList<TransactionDTO> result = instance.viewOrderTransactions(order1Id);
        
        // Check actual against expected values
        boolean passed = true;
        
        if ( 
            !result.get(0).getName().equalsIgnoreCase("Picked up") ||
            !result.get(1).getName().equalsIgnoreCase("Dropped off") ||
                
            result.get(0).getOrderId()!= order1Id ||
            result.get(1).getOrderId()!= order1Id 
                
        ) {
            passed = false;
        }
        
        // Tidy up, remove order that was created
        sellerInstance.deleteOrder(order1Id);
        
        assertTrue(passed);
    }

    @Test
    public void testGetDate() {
        System.out.println("__ getDate");

        RecipientBean instance = new RecipientBean();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();

        assertEquals(expResult, result);
    }

    @Test
    public void testViewOrderParcels() {
        System.out.println("__ viewOrderParcels");
        
        RecipientBean instance = new RecipientBean();
        SellerBean sellerInstance = new SellerBean();
        
        // Prep order info for this recipient
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        
        // Create orders
        int order1Id = sellerInstance.getNextOrderId();
        sellerInstance.createOrder();
        
        // Prep parcel info
        sellerInstance.setName("Book");
        sellerInstance.setType("Large");
        sellerInstance.setWeightGrams(50);
        sellerInstance.setSellerId(3);
        
        // Create parcels
        int parcel1Id = sellerInstance.getNextParcelId();
        sellerInstance.createParcel();
        
        int parcel2Id = sellerInstance.getNextParcelId();
        sellerInstance.createParcel();
        
        // Add parcels to order
        sellerInstance.addParcelToOrder(order1Id, parcel1Id, 20);
        sellerInstance.addParcelToOrder(order1Id, parcel2Id, 10);
        
        // Find parcels against this order
        ArrayList<ParcelDTO> result = instance.viewOrderParcels(order1Id);
        
        // Check actual against expected values
        boolean passed = true;
        
        if ( 
            !result.get(0).getName().equalsIgnoreCase("Book") ||
            result.get(0).getQuantityInOrder() != 20 ||
                
            !result.get(1).getName().equalsIgnoreCase("Book") ||
            result.get(1).getQuantityInOrder() != 10 
        ) {
            passed = false;
        }
        
        // Tidy up, remove order that was created
        sellerInstance.deleteOrder(order1Id);
        
        assertTrue(passed);
    }

    @Test /* T20 */
    public void testViewRecipientOrders() {
        System.out.println("T20 - viewRecipientOrders");
        
        RecipientBean instance = new RecipientBean();
        RegisterBean registerInstance = new RegisterBean();
        SellerBean sellerInstance = new SellerBean();
        
        // Create new recipient
        int newRecipientId = registerInstance.getNextId();
        // Load values to use with register
        registerInstance.setFirstName("Test");
        registerInstance.setLastName("Test");
        registerInstance.setUsername("newRecipient");
        registerInstance.setPassword1("pass");
        registerInstance.setPassword2("pass");
        registerInstance.setAddressLineOne("123 Road Name");
        registerInstance.setTown("Basingstoke");
        registerInstance.setCounty("Hants");
        registerInstance.setPostcode("RG112AA");
        registerInstance.setEmail("a@b.com");
        registerInstance.setPhone("01234567890");
        registerInstance.register();
        
        // Prep order details for this recipient
        sellerInstance.setRecipientId(newRecipientId);
        sellerInstance.setSellerId(3);
        
        // Create orders
        int order1Id = sellerInstance.getNextOrderId();
        sellerInstance.createOrder();
        
        int order2Id = sellerInstance.getNextOrderId();
        sellerInstance.createOrder();
        
        // View recipientOrders to find list of all valid orders
        ArrayList<OrderDTO> result = instance.viewRecipientOrders(newRecipientId);
        
        // Perform checks to see exp matches actual results
        boolean passed = true;
        
        if ( 
            !result.get(0).getRecipient().getUsername().equalsIgnoreCase("newRecipient") ||
            !result.get(0).getSeller().getUsername().equalsIgnoreCase("seller") ||

            !result.get(1).getRecipient().getUsername().equalsIgnoreCase("newRecipient") ||
            !result.get(1).getSeller().getUsername().equalsIgnoreCase("seller") 
        ) {
            passed = false;
        }
        
        // Tidy up, remove all orders that were created (2)
        sellerInstance.deleteOrder(order1Id);
        sellerInstance.deleteOrder(order2Id);
        
        assertTrue(passed);
    }

    @Test
    public void testSetAndGetTotalOrders() {
        System.out.println("__ TotalOrders");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 5;
        instance.setTotalOrders(expResult);
        int result = instance.getTotalOrders();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetOrderDetails() {
        System.out.println("__ OrderDetails");
        
        RecipientBean instance = new RecipientBean();
        
        OrderDTO expResult = null;
        instance.setOrderDetails(expResult);
        OrderDTO result = instance.getOrderDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetUserDetails() {
        System.out.println("__ UserDetails");
        
        RecipientBean instance = new RecipientBean();
        
        UserDTO expResult = null;
        instance.setUserDetails(expResult);
        UserDTO result = instance.getUserDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("__ Id");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRecipientId() {
        System.out.println("__ RecipientId");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 1;
        instance.setRecipientId(expResult);
        int result = instance.getRecipientId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetSellerId() {
        System.out.println("__ SellerId");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 3;
        instance.setSellerId(expResult);
        int result = instance.getSellerId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetParcelDetails() {
        System.out.println("__ ParcelDetails");
        
        RecipientBean instance = new RecipientBean();
        
        ParcelDTO expResult = null;
        instance.setParcelDetails(expResult);
        ParcelDTO result = instance.getParcelDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalParcels() {
        System.out.println("__ TotalParcels");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 5;
        instance.setTotalParcels(expResult);
        int result = instance.getTotalParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalTransactions() {
        System.out.println("__ TotalTransactions");
        
        RecipientBean instance = new RecipientBean();
        
        int expResult = 5;
        instance.setTotalTransactions(expResult);
        int result = instance.getTotalTransactions();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRole() {
        System.out.println("__ Role");
        
        RecipientBean instance = new RecipientBean();
        
        String expResult = "Driver";
        instance.setRole(expResult);
        String result = instance.getRole();
        
        assertEquals(expResult, result);
    }    
}
