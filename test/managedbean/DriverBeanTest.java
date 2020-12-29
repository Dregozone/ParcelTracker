package managedbean;

import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class DriverBeanTest {
    
    public DriverBeanTest() {
    }

    @Test
    public void testFindUser() {
        System.out.println("___ findUser");
        
        int userID = 2;
        
        DriverBean instance = new DriverBean();
        
        String expResult = "driver";
        UserDTO result = instance.findUser(userID);
        
        assertEquals(expResult, result.getUsername());
    }

    @Test
    public void testFindRoleByUser() {
        System.out.println("___ findRoleByUser");
        
        int userID = 2;
        
        DriverBean instance = new DriverBean();
        String expResult = "Driver";
        
        String result = instance.findRoleByUser(userID);
        
        assertEquals(expResult, result);
    }

    @Test /* T27 */
    public void testAddAndDeleteTransaction() {
        System.out.println("T27 - addAndRemoveTransaction");
        
        DriverBean instance = new DriverBean();
        SellerBean sellerInstance = new SellerBean();
        LoginBean loginInstance = new LoginBean();
        
        // Login
        loginInstance.setUsername("driver");
        
        // Prep order
        int orderId = sellerInstance.getNextOrderId();
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        
        // Create order
        sellerInstance.createOrder();
        OrderDTO orderDetails = sellerInstance.getOrderDetails();
        
        // Add transaction
        int currentNextTransactionId = instance.getNextTransactionId();
        instance.addTransaction(orderId, "Picked up", 2);
        TransactionDTO transaction = new TransactionDTO(currentNextTransactionId, orderId, "Picked up", sellerInstance.findUser(2), "");

        // Perform checks actual against expected
        boolean passed = true;
        
        if (
            transaction.getId() != currentNextTransactionId ||
            !transaction.getName().equalsIgnoreCase("Picked up") ||
            transaction.getOrderId() != orderId
        ) {
            passed = false;
        }
        
        //System.out.println( transaction.getId() + ", " + currentNextTransactionId );
        //System.out.println( orderDetails );
        //System.out.println( transaction );
        
        
        /* *** Can not delete transaction because of injection requirement for manager *** */
        // Perform deletion
        //instance.deleteTransaction(transaction.getId(), "Driver", orderDetails, transaction);
        
        // Deletion checks
        //ArrayList<TransactionDTO> transactions = sellerInstance.getTransactionByOrder(orderId);
        
        // Pass check
        /*
        if ( (transactions.size() > 0) ) { // No transactions should remain against this order
            passed = false;
        }
        */
        
        // Tidy up
        sellerInstance.deleteOrder(orderId);
        
        assertTrue(passed);
    }

    @Test
    public void testGetNextTransactionId() {
        System.out.println("___ getNextTransactionId");
        
        DriverBean instance = new DriverBean();
        
        int result = instance.getNextTransactionId();
        
        assertTrue(result > 0); // Test that a positive integer is returnedas the next available ID
    }

    @Test
    public void testGetNextOrderParcelsId() {
        System.out.println("___ getNextOrderParcelsId");
        
        DriverBean instance = new DriverBean();
        
        int result = instance.getNextOrderParcelsId();
        
        assertTrue(result > 0); // Test that a positive integer is returned
    }

    @Test
    public void testGetNextId() {
        System.out.println("___ getNextId");
        
        DriverBean instance = new DriverBean();
        
        int result = instance.getNextId();
        
        assertTrue(result > 0); // Test that a positive integer is returned
    }

    @Test
    public void testGetDate() {
        System.out.println("___ getDate");

        DriverBean instance = new DriverBean();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();

        assertEquals(expResult, result);
    }

    @Test
    public void testGetOrderParcelByOrder() {
        System.out.println("___ getOrderParcelByOrder");
        
        DriverBean instance = new DriverBean();
        SellerBean sellerInstance = new SellerBean();
        
        // Prep order
        int orderId = sellerInstance.getNextOrderId();
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        
        // Create order
        sellerInstance.createOrder();
        
        // Prep parcel
        int parcelId = sellerInstance.getNextParcelId();
        sellerInstance.setName("NewPackage");
        sellerInstance.setType("NewType");
        sellerInstance.setWeightGrams(50);
        sellerInstance.setSellerId(3);
        
        // Create parcel
        sellerInstance.createParcel();
        
        // Add parcel to order
        sellerInstance.addParcelToOrder(orderId, parcelId, 2);
        
        // Find parcels against this order for checking
        ArrayList<ParcelDTO> parcels = instance.getOrderParcelByOrder(orderId);
        
        // Check values, expected against actual
        boolean passed = true;
        
        if (
            parcels.size() != 1 || /* There should only be one parcel included */ 
            !parcels.get(0).getName().equalsIgnoreCase("NewPackage") ||
            !parcels.get(0).getType().equalsIgnoreCase("NewType") ||
            parcels.get(0).getWeightGrams() != 50 ||
            !parcels.get(0).getSeller().getUsername().equalsIgnoreCase("seller") /* Check some parcel details here... */
        ) {
            passed = false;
        }
        
        // Tidy up
        sellerInstance.deleteOrder(orderId);
        
        assertTrue( passed );
    }

    @Test
    public void testGetOrderSummaries() {
        System.out.println("___ getOrderSummaries");
        
        DriverBean instance = new DriverBean();
        
        // Find all orders
        instance.getOrderSummaries();
        
        int totalOrders = instance.getTotalOrders();
        
        assertTrue(totalOrders > 0); // There is at least 1 valid order returned
    }
    
    @Test 
    public void testViewOrderProgress() {
        System.out.println("___ viewOrderProgress");
        
        DriverBean instance = new DriverBean();
        SellerBean sellerInstance = new SellerBean();
        LoginBean loginInstance = new LoginBean();
        
        // Login
        loginInstance.setUsername("driver");
        
        // Prep order
        int orderId = sellerInstance.getNextOrderId();
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        
        // Create order
        sellerInstance.createOrder();
        OrderDTO orderDetails = sellerInstance.getOrderDetails();
        
        // Add transaction
        int currentNextTransactionId = instance.getNextTransactionId();
        instance.addTransaction(orderId, "Picked up", 2);
        TransactionDTO transaction = new TransactionDTO(currentNextTransactionId, orderId, "Picked up", sellerInstance.findUser(2), "");

        // Perform checks actual against expected
        boolean passed = true;
        
        if (
            transaction.getId() != currentNextTransactionId ||
            !transaction.getName().equalsIgnoreCase("Picked up") ||
            transaction.getOrderId() != orderId
        ) {
            passed = false;
        }
        
        // Find all transations and progress against this order
        ArrayList<TransactionDTO> transactions = instance.getTransactionByOrder(orderId);
        
        // Find if there are transactions to be displayed (there should be 1)
        if ( transactions.size() != 1 ) {
            passed = false;
        }
        
        assertTrue(passed);
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("___ Id");
        
        DriverBean instance = new DriverBean();
        
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRecipientId() {
        System.out.println("___ RecipientId");
        
        DriverBean instance = new DriverBean();
        
        int expResult = 1;
        instance.setRecipientId(expResult);
        int result = instance.getRecipientId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetSellerId() {
        System.out.println("___ SellerId");
        
        DriverBean instance = new DriverBean();
        
        int expResult = 3;
        instance.setSellerId(expResult);
        int result = instance.getSellerId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetParcelDetails() {
        System.out.println("___ ParcelDetails");
        
        DriverBean instance = new DriverBean();
        
        ParcelDTO expResult = null;
        instance.setParcelDetails(expResult);    
        ParcelDTO result = instance.getParcelDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalParcels() {
        System.out.println("___ TotalParcels");
        
        DriverBean instance = new DriverBean();
        
        int expResult = 5;
        instance.setTotalParcels(expResult);
        int result = instance.getTotalParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalTransactions() {
        System.out.println("___ TotalTransactions");
        DriverBean instance = new DriverBean();
        
        int expResult = 5;
        instance.setTotalTransactions(expResult);
        int result = instance.getTotalTransactions();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRole() {
        System.out.println("___ Role");
        
        DriverBean instance = new DriverBean();
        
        String expResult = "Driver";
        instance.setRole(expResult);
        String result = instance.getRole();
        
        assertEquals(expResult, result);
    }
}
