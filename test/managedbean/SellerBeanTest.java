package managedbean;

import dto.MetricDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import dto.UserDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class SellerBeanTest {
    
    public SellerBeanTest() {
    }

    @Test
    public void testFindRoleByUser() {
        System.out.println("__ findRoleByUser");
        
        int userID = 3;
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Seller";
        String result = instance.findRoleByUser(userID);
        
        assertEquals(expResult, result);
    }

    @Test /* T24 */
    public void testViewAllUsers() {
        System.out.println("T24 - viewAllUsers");
        
        SellerBean instance = new SellerBean();
        
        // Find all users
        instance.viewAllUsers();
        
        int usersFound = instance.getTotalUsers();
        
        assertTrue(usersFound > 0); // At least one valid user was found
    }

    @Test /* T28 */
    public void testDeleteParcel() {
        System.out.println("T28 - deleteParcel");
        
        SellerBean instance = new SellerBean();
        
        // Load values for create parcel
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(50);
        instance.setSellerId(3);
        
        // Create parcel
        int parcelId = instance.getNextParcelId();
        instance.createParcel();
        
        // Delete the newly created parcel
        instance.deleteParcel(parcelId);
        
        // Search DB for parcel by its ID
        ParcelDTO result = instance.findParcelById(parcelId);
        
        // Check it no longer exists
        assertNull(result);
    }

    /* Test:T7 */
    @Test
    public void testCreateEditParcelCreateEditOrderAddParcelToOrderValid() {
        System.out.println("T7 - createParcel (valid), editParcel (valid), createOrder (valid), editOrder (valid), addParcelToOrder");
        
        SellerBean instance = new SellerBean();
        
        // Load initial values for create parcel
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(50);
        instance.setSellerId(3);
        
        // Create parcel
        int currentNextParcelId = instance.getNextParcelId();
        instance.createParcel();
        int newNextParcelId = instance.getNextParcelId();
        
        // Load new values for edit parcel
        instance.setName("Pen");
        instance.setType("Small");
        instance.setWeightGrams(100);
        instance.setSellerId(2);
        
        // Edit Parcel
        instance.editParcel(currentNextParcelId);
        ParcelDTO addedParcel = instance.getParcelDetails();
        
        // Load initial values for create order
        instance.setRecipientId(1);
        instance.setSellerId(3);
        
        // Create order
        int currentNextOrderId = instance.getNextOrderId();
        instance.createOrder();
        int newNextOrderId = instance.getNextOrderId();
        
        // Load new values for edit order
        instance.setRecipientId(2);
        instance.setSellerId(1);
        
        // Edit order
        instance.editOrder(currentNextOrderId);        
        
        // Add parcel to order
        instance.addParcelToOrder(currentNextOrderId, currentNextParcelId, 2);
        
        // Check whether the created parcel is in the created order (Check for success)
        ParcelDTO fetchedParcel = instance.getOrderParcelByOrder(currentNextOrderId).get(0);
        
        // Check for success
        if (
                ( newNextParcelId > currentNextParcelId ) && /* New parcel was added */
                ( newNextOrderId > currentNextOrderId ) && /* New order was added */
                ( addedParcel.getName().equals( fetchedParcel.getName() ) ) /* Order with ID={currentNextOrderId} has a parcel with ID={currentNextParcelId} and qty=2 */
        ) {
            // Success
            assertTrue(true); // Pass
        } else {
            // Failure
            
            System.out.println("Checking " + newNextParcelId + " > " + currentNextParcelId );
            System.out.println("Checking " + newNextOrderId + " > " + currentNextOrderId );
            System.out.println("Checking " + addedParcel.getName() + " .equals " + fetchedParcel.getName() );
            
            fail("Failed!");
        }
    }
    
    /* Test:T3 */
    @Test
    public void testCreateParcelInvalidWeight() {
        System.out.println("T3 - createParcel (invalid weight)");
        
        SellerBean instance = new SellerBean();
        
        instance.setName("Booke");
        instance.setType("Largee");
        instance.setWeightGrams(-1);
        instance.setSellerId(3);
        
        int currentNextParcelId = instance.getNextParcelId();
        
        // Run
        try {
            instance.createParcel();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        int newNextParcelId = instance.getNextParcelId();
        
        assertTrue(newNextParcelId == currentNextParcelId); // No new parcel record was added and nextParcelId pointer remains the same as before the insert request
    }
    
    /* Test:T4 */
    @Test
    public void testCreateParcelInvalidSellerId() {
        System.out.println("T4 - createParcel (invalid sellerID)");
        
        SellerBean instance = new SellerBean();
        
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(0);
        instance.setSellerId(-1);
        
        int currentNextParcelId = instance.getNextParcelId();
        
        // Run
        try {
            instance.createParcel();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        int newNextParcelId = instance.getNextParcelId();
        
        assertTrue(newNextParcelId == currentNextParcelId); // No new parcel record was added and nextParcelId pointer remains the same as before the insert request
    }
    
    /* Test:T5 */ ////
    @Test
    public void testEditParcelInvalidWeight() {
        System.out.println("T5 - editParcel (invalid weight)");
        
        SellerBean instance = new SellerBean();
        
        instance.setName("Booke");
        instance.setType("Largee");
        instance.setWeightGrams(0);
        instance.setSellerId(3);
        
        int currentNextParcelId = instance.getNextParcelId();
        
        // Run
        try {
            instance.createParcel();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            System.out.print("Caught the NullPointerException!!!");
        }
        
        // Edit parcel with invalid values
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(-1); // BAD INPUT
        instance.setSellerId(3);
        
        // Run
        try {
            instance.editParcel(currentNextParcelId);
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            System.out.print("Caught the NullPointerException!!!");
        }
        
        
        // Find parcel details
        ParcelDTO result = instance.findParcelById(currentNextParcelId);
        
        // Perform weight checks
        boolean passed = true;
        
        if (
            result.getWeightGrams() < 0 /* This is an illegal input */
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
    
    /* Test:T6 */ ////
    @Test
    public void testEditParcelInvalidSellerId() {
        System.out.println("T6 - eidtParcel (invalid sellerID)");
        
        SellerBean instance = new SellerBean();
        
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(0);
        instance.setSellerId(3);
        
        int currentNextParcelId = instance.getNextParcelId();
        
        // Run
        try {
            instance.createParcel();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Edit parcel with invalid values
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(0);
        instance.setSellerId(-1); // BAD INPUT
        
        // Run
        try {
            instance.editParcel(currentNextParcelId);
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            System.out.print("Caught the NullPointerException!!!");
            
            return; // Its good that this failed, due to bad input
        }
        
        fail("Failed to catch bad input");
    }

    @Test /* T34 */
    public void testViewAllParcels() {
        System.out.println("T34 - viewAllParcels");
        
        SellerBean instance = new SellerBean();
        
        // Find all parcels
        instance.viewAllParcels();
        
        int parcelsFound = instance.getTotalParcels();
        
        assertTrue(parcelsFound > 0); // At least one valid parcel was found
    }

    @Test /* T35 */
    public void testFindParcelById() {
        System.out.println("T35 - findParcelById");
        
        int parcelId = 1; // We know the expected values of this parcel already so can test against it
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO result = instance.findParcelById(parcelId);
        
        boolean passed = true;
        
        if ( 
            !result.getName().equalsIgnoreCase("Item One") ||
            !result.getType().equalsIgnoreCase("Large Box") 
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }

    @Test /* T31 */
    public void testViewDriverMetrics() {
        System.out.println("T31 - viewDriverMetrics");
        
        SellerBean instance = new SellerBean();
        RegisterBean registerInstance = new RegisterBean();
        LoginBean loginInstance = new LoginBean();
        DriverBean driverInstance = new DriverBean();
        
        // Create users
        int bobId = registerInstance.getNextId();
        // Load values to use with register
        registerInstance.setFirstName("Test");
        registerInstance.setLastName("Test");
        registerInstance.setUsername("Bob");
        registerInstance.setPassword1("pass");
        registerInstance.setPassword2("pass");
        registerInstance.setAddressLineOne("123 Road Name");
        registerInstance.setTown("Basingstoke");
        registerInstance.setCounty("Hants");
        registerInstance.setPostcode("RG112AA");
        registerInstance.setEmail("a@b.com");
        registerInstance.setPhone("01234567890");
        registerInstance.register();
        
        
        int daveId = registerInstance.getNextId();
        // Load values to use with register
        registerInstance.setFirstName("Test");
        registerInstance.setLastName("Test");
        registerInstance.setUsername("Dave");
        registerInstance.setPassword1("pass");
        registerInstance.setPassword2("pass");
        registerInstance.setAddressLineOne("123 Road Name");
        registerInstance.setTown("Basingstoke");
        registerInstance.setCounty("Hants");
        registerInstance.setPostcode("RG112AA");
        registerInstance.setEmail("a@b.com");
        registerInstance.setPhone("01234567890");
        registerInstance.register();
        
        int jimmyId = registerInstance.getNextId();
        // Load values to use with register
        registerInstance.setFirstName("Test");
        registerInstance.setLastName("Test");
        registerInstance.setUsername("Jimmy");
        registerInstance.setPassword1("pass");
        registerInstance.setPassword2("pass");
        registerInstance.setAddressLineOne("123 Road Name");
        registerInstance.setTown("Basingstoke");
        registerInstance.setCounty("Hants");
        registerInstance.setPostcode("RG112AA");
        registerInstance.setEmail("a@b.com");
        registerInstance.setPhone("01234567890");
        registerInstance.register();
        
        int sallyId = registerInstance.getNextId();
        // Load values to use with register
        registerInstance.setFirstName("Test");
        registerInstance.setLastName("Test");
        registerInstance.setUsername("Sally");
        registerInstance.setPassword1("pass");
        registerInstance.setPassword2("pass");
        registerInstance.setAddressLineOne("123 Road Name");
        registerInstance.setTown("Basingstoke");
        registerInstance.setCounty("Hants");
        registerInstance.setPostcode("RG112AA");
        registerInstance.setEmail("a@b.com");
        registerInstance.setPhone("01234567890");
        registerInstance.register();
        
        
        // Load values for create order
        instance.setRecipientId(jimmyId);
        instance.setSellerId(sallyId);
        
        
        // Create orders
        int order1Id = instance.getNextOrderId();
        instance.createOrder();
        
        int order2Id = instance.getNextOrderId();
        instance.createOrder();
        
        int order3Id = instance.getNextOrderId();
        instance.createOrder();
        
        
        // Add transactions to orders
        driverInstance.addTransaction(order1Id, "Picked up", bobId);
        driverInstance.addTransaction(order1Id, "Dropped off", bobId);
        
        driverInstance.addTransaction(order2Id, "Picked up", bobId);
        driverInstance.addTransaction(order2Id, "Dropped off", bobId);
        
        driverInstance.addTransaction(order3Id, "Picked up", daveId);
        driverInstance.addTransaction(order3Id, "Dropped off", daveId);
        
        // Find driver metrics
        ArrayList<MetricDTO> results = instance.viewDriverMetrics();
        
        boolean passed = true;
        
        // Check values against expected
        if (
                !results.get(0).getName().equalsIgnoreCase("Bob") || 
                results.get(0).getDeliveryCount() != 2 ||
                results.get(0).getDaysToComplete() != 0 ||
                
                !results.get(1).getName().equalsIgnoreCase("Dave") || 
                results.get(1).getDeliveryCount() != 1 ||
                results.get(1).getDaysToComplete() != 0 
        ) {
            passed = false;
        }
        
        // Clean up, delete these orders and transactionsto avoid cluttering the system
        instance.deleteOrder(order1Id);
        instance.deleteOrder(order2Id);
        instance.deleteOrder(order3Id);
        
        // Check if this has returned the correctly calculated values
        assertTrue(passed);
    }

    @Test
    public void testGetNextOrderParcelsId() {
        System.out.println("__ getNextOrderParcelsId");
        
        SellerBean instance = new SellerBean();
        
        int result = instance.getNextOrderParcelsId();
        
        assertTrue(result > 0); // A positive next parcel ID is returned
    }

    @Test
    public void testGetDate() {
        System.out.println("__ getDate");

        SellerBean instance = new SellerBean();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();

        assertEquals(expResult, result);
    }

    @Test /* T29 */
    public void testDeleteOrder() {
        System.out.println("T29 - deleteOrder");
        
        SellerBean instance = new SellerBean();
        
        // Load values for create order
        instance.setRecipientId(1);
        instance.setSellerId(3);
        
        // Create order
        int orderId = instance.getNextOrderId();
        instance.createOrder();
        
        // Delete the newly created order
        instance.deleteOrder(orderId);
        
        // Search DB for order by its ID
        OrderDTO result = instance.findOrderById(orderId);
        
        // Check it no longer exists
        assertNull(result);
    }

    @Test /* T32 */
    public void testViewAllOrders() {
        System.out.println("T32 - viewAllOrders");
        
        SellerBean instance = new SellerBean();
        
        // Find all orders
        instance.viewAllOrders();
        
        int ordersFound = instance.getTotalOrders();
        
        assertTrue(ordersFound > 0); // At least one valid order was found
    }

    @Test /* T33 */
    public void testFindOrderById() {
        System.out.println("T33 - findOrderById");
        
        int orderId = 1; // We know the expected values of this order already so can test against it
        
        SellerBean instance = new SellerBean();
        
        OrderDTO result = instance.findOrderById(orderId);
        
        boolean passed = true;
        
        if ( 
            !result.getRecipient().getUsername().equalsIgnoreCase("recipient") ||
            !result.getSeller().getUsername().equalsIgnoreCase("recipient") 
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }

    @Test
    public void testSetAndGetTotalOrders() {
        System.out.println("__ TotalOrders");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalOrders(expResult);
        int result = instance.getTotalOrders();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("__ Id");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRecipientId() {
        System.out.println("__ RecipientId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setRecipientId(expResult);
        int result = instance.getRecipientId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRole() {
        System.out.println("__ Role");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Driver";
        instance.setRole(expResult);
        String result = instance.getRole();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetSellerId() {
        System.out.println("__ SellerId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 3;
        instance.setSellerId(expResult);
        int result = instance.getSellerId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetParcelDetails() {
        System.out.println("__ ParcelDetails");
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO expResult = null;
        instance.setParcelDetails(expResult);
        ParcelDTO result = instance.getParcelDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalUsers() {
        System.out.println("__ TotalUsers");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalUsers(expResult);
        int result = instance.getTotalUsers();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalParcels() {
        System.out.println("__ TotalParcels");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalParcels(expResult);
        int result = instance.getTotalParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalTransactions() {
        System.out.println("__ TotalTransactions");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 0;
        instance.setTotalTransactions(expResult);
        int result = instance.getTotalTransactions();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetName() {
        System.out.println("__ Name");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "name";
        instance.setName(expResult);
        String result = instance.getName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetType() {
        System.out.println("__ Type");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "type";
        instance.setType(expResult);
        String result = instance.getType();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWeightGrams() {
        System.out.println("__ WeightGrams");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 50;
        instance.setWeightGrams(expResult);
        int result = instance.getWeightGrams();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetQuantity() {
        System.out.println("__ Quantity");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 2;
        instance.setQuantity(expResult);
        int result = instance.getQuantity();
        
        assertEquals(expResult, result);
    }

    @Test /* T25 ???? */
    public void testSetAndGetUserDetails() {
        System.out.println("T25???? - UserDetails");
        
        SellerBean instance = new SellerBean();
        
        UserDTO expResult = null;
        instance.setUserDetails(expResult);
        UserDTO result = instance.getUserDetails();
        
        assertEquals(expResult, result);
    }    
}
