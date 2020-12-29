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
        System.out.println("___ findRoleByUser");
        
        int userID = 3;
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Seller";
        String result = instance.findRoleByUser(userID);
        
        assertEquals(expResult, result);
    }

    @Test /* T24 */
    public void testViewAllUsers() {
        System.out.println("T24 - testViewAllUsers");
        
        SellerBean instance = new SellerBean();
        
        // Find all users
        instance.viewAllUsers();
        
        int usersFound = instance.getTotalUsers();
        
        assertTrue(usersFound > 0); // At least one valid user was found
    }

    @Test /* T15 */
    public void testFindOrderParcels() {
        System.out.println("T15 - testFindOrderParcels");
        
        SellerBean instance = new SellerBean();
        
        ArrayList<ParcelDTO> result = null;
        
        // Run
        try {
            // Search DB for parcels by order ID
            result = instance.getOrderParcelByOrder(3);
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        assertTrue( result.isEmpty() );
    }
    
    @Test /* T16 */
    public void testFindOrderTransactionsNoTransactions() {
        System.out.println("T16 - testFindOrderTransactionsNoTransactions");
        
        int orderId = 3;
        
        SellerBean instance = new SellerBean();
        DriverBean driverInstance = new DriverBean();
        
        ArrayList<TransactionDTO> result = null;
        
        // Run
        try {
            // Search DB for transactions by order ID
            result = driverInstance.getTransactionByOrder(orderId);
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        OrderDTO orderDetails = instance.findOrderById(orderId);
        
        assertTrue( 
            result.isEmpty() && /* There are no transactions against the order */ 
            orderDetails.getDriver().getUsername().equalsIgnoreCase("None") /* There is no named driver against this order */
        );
    }
    
    @Test /* T17 */
    public void testFindOrderTransactionsNoTransactionNoDriver() {
        System.out.println("T17 - testFindOrderTransactionNoDriver");
        
        int orderId = 3;
        
        SellerBean instance = new SellerBean();
        
        OrderDTO orderDetails = instance.findOrderById(orderId);
        
        assertTrue( orderDetails.getDriver().getUsername().equalsIgnoreCase("None") ); /* There is no named driver against this order */
    }
    
    @Test /* T18 */
    public void testFindOrderTransactionsSomeTransactions() {
        System.out.println("T18 - testFindOrderTransactionsSomeTransactions");
        
        int orderId = 2;
        
        SellerBean instance = new SellerBean();
        DriverBean driverInstance = new DriverBean();
        
        ArrayList<TransactionDTO> result = null;
        
        // Add a transaction against the order to ensure there is at least 1 transaction here
        driverInstance.addTransaction(orderId, "Picked up", 2);
        
        // Run
        try {
            // Search DB for transactions by order ID
            result = driverInstance.getTransactionByOrder(orderId);
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        OrderDTO orderDetails = instance.findOrderById(orderId);
        
        assertTrue( 
            result.size() >= 1 && /* There is at least 1 transaction against the order */ 
            orderDetails.getDriver().getUsername().equalsIgnoreCase("driver") /* "driver" is named against this order */
        );
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
        System.out.println(" T7 - testCreateEditParcelCreateEditOrderAddParcelToOrder"); /* createParcel (valid), editParcel (valid), createOrder (valid), editOrder (valid), addParcelToOrder */
        
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
    
    /* Test:T8 */
    @Test
    public void testCreateOrderInvalidRecipientId() {
        System.out.println(" T8 - testCreateOrderInvalidRecipientId");
        
        SellerBean instance = new SellerBean();
        
        // Prep order details to be added
        instance.setRecipientId(-1);
        instance.setSellerId(1);
        
        int currentNextOrderId = instance.getNextOrderId();
        
        // Run
        try {
            instance.createOrder();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        int newNextOrderId = instance.getNextOrderId();
        
        assertTrue(newNextOrderId == currentNextOrderId); // No new order record was added and nextOrderId pointer remains the same as before the insert request
    }
    
    /* Test:T9 */
    @Test
    public void testCreateOrderInvalidSellerId() {
        System.out.println(" T9 - testCreateOrderInvalidSellerId");
        
        SellerBean instance = new SellerBean();
        
        // Prep order details to be added
        instance.setRecipientId(1);
        instance.setSellerId(-1);
        
        int currentNextOrderId = instance.getNextOrderId();
        
        // Run
        try {
            instance.createOrder();
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        int newNextOrderId = instance.getNextOrderId();
        
        assertTrue(newNextOrderId == currentNextOrderId); // No new order record was added and nextOrderId pointer remains the same as before the insert request
    }
    
    /* Test:T10 */
    @Test
    public void testEditOrderInvalidRecipientId() {
        System.out.println("T10 - testEditOrderInvalidRecipientId");
        
        SellerBean instance = new SellerBean();
        
        // Prep order details to be added
        instance.setRecipientId(1);
        instance.setSellerId(1);
        
        // Create order
        int currentNextOrderId = instance.getNextOrderId();
        instance.createOrder();
        
        // Prep edit order values
        instance.setRecipientId(-1);
        instance.setSellerId(1);
        
        // Run
        boolean passed = true;
        
        try {
            instance.editOrder(currentNextOrderId); 
            
            // If an edit was carried out then this is bad, should have returned NULL due to illegal entry value
            passed = false;
            
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Tidy up, delete this order once tested
        instance.deleteOrder(currentNextOrderId);
        
        assertTrue(passed);
    }
    
    /* Test:T11 */
    @Test
    public void testEditOrderInvalidSellerId() {
        System.out.println("T11 - testEditOrderInvalidSellerId");
        
        SellerBean instance = new SellerBean();
        
        // Prep order details to be added
        instance.setRecipientId(1);
        instance.setSellerId(1);
        
        // Create order
        int currentNextOrderId = instance.getNextOrderId();
        instance.createOrder();
        
        // Prep edit order values
        instance.setRecipientId(1);
        instance.setSellerId(-1);
        
        // Run
        boolean passed = true;
        
        try {
            instance.editOrder(currentNextOrderId); 
            
            // If an edit was carried out then this is bad, should have returned NULL due to illegal entry value
            passed = false;
            
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        // Tidy up, delete this order once tested
        instance.deleteOrder(currentNextOrderId);
        
        assertTrue(passed);
    }
    
    /* Test:T12 */
    @Test
    public void testAddParcelToOrderInvalidOrderId() {
        System.out.println("T12 - testEditOrderInvalidOrderId");
        
        SellerBean instance = new SellerBean();
        
        boolean passed = true;
        
        try {
            instance.addParcelToOrder(-1, 1, 1);
            
            // If an edit was carried out then this is bad, should have returned NULL due to illegal entry value
            passed = false;
            
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        assertTrue(passed);
    }
    
    /* Test:T13 */
    @Test
    public void testAddParcelToOrderInvalidParcelId() {
        System.out.println("T13 - testEditOrderInvalidParcelId");
        
        SellerBean instance = new SellerBean();
        
        boolean passed = true;
        
        try {
            instance.addParcelToOrder(1, -1, 1);
            
            // If an edit was carried out then this is bad, should have returned NULL due to illegal entry value
            passed = false;
            
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        assertTrue(passed);
    }
    
    /* Test:T14 */
    @Test
    public void testAddParcelToOrderInvalidQuantity() {
        System.out.println("T14 - testEditOrderInvalidQuantity");
        
        SellerBean instance = new SellerBean();
        
        boolean passed = true;
        
        try {
            instance.addParcelToOrder(1, 1, -1);
            
            // If an edit was carried out then this is bad, should have returned NULL due to illegal entry value
            passed = false;
            
        } catch (NullPointerException e) { // Catch when the returned value is NULL, this is expected on invalid input
            //System.out.print("Caught the NullPointerException!!!");
        }
        
        assertTrue(passed);
    }
    
    /* Test:T3 */
    @Test
    public void testCreateParcelInvalidWeight() {
        System.out.println(" T3 - testCreateParcelInvalidWeight"); /* createParcel (invalid weight) */
        
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
        System.out.println(" T4 - testCreateParcelInvalidSellerId"); /* createParcel (invalid sellerID) */
        
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
        System.out.println(" T5 - testEditParcelInvalidWeight"); /* editParcel (invalid weight) */
        
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
        System.out.println(" T6 - testEditParcelInvalidSellerId"); /* editParcel (invalid sellerID) */
        
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
            //System.out.print("Caught the NullPointerException!!!");
            
            return; // Its good that this failed, due to bad input
        }
        
        fail("Failed to catch bad input");
    }

    @Test /* T33 */
    public void testViewAllParcels() {
        System.out.println("T33 - testViewAllParcels");
        
        SellerBean instance = new SellerBean();
        
        // Find all parcels
        instance.viewAllParcels();
        
        int parcelsFound = instance.getTotalParcels();
        
        assertTrue(parcelsFound > 0); // At least one valid parcel was found
    }

    @Test /* T34 */
    public void testFindParcelById() {
        System.out.println("T34 - testFindParcelById");
        
        int parcelId = 2; // We know the expected values of this parcel already so can test against it
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO result = instance.findParcelById(parcelId);
        
        boolean passed = true;
        
        if ( 
            !result.getName().equalsIgnoreCase("Item Two") ||
            !result.getType().equalsIgnoreCase("Small Letter") 
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }

    @Test /* T26 */
    public void testViewRecipientOrdersSpecial() {
        System.out.println("T26 - viewRecipientOrdersSpecial");
        
        RecipientBean recipientInstance = new RecipientBean();
        RegisterBean registerInstance = new RegisterBean();
        SellerBean sellerInstance = new SellerBean();
        LoginBean loginInstance = new LoginBean();
        
        // Create new recipient
        int newRecipientId = registerInstance.getNextId();
        // Load values to use with register
        // Recipient
        int jimmyId = registerInstance.getNextId();
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
        
        // Seller
        int sallyId = registerInstance.getNextId();
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
        
        // Prep order details for this recipient
        sellerInstance.setRecipientId(jimmyId);
        sellerInstance.setSellerId(sallyId);
        
        // Create orders
        int orderId = sellerInstance.getNextOrderId();
        sellerInstance.createOrder();
        
        // Login as Jimmy
        loginInstance.setUsername("Jimmy");
        
        // View recipientOrders to find list of all valid orders
        ArrayList<OrderDTO> result = recipientInstance.viewRecipientOrders(jimmyId);
        int countOrders = result.size();
        
        // Perform checks to see exp matches actual results
        boolean passed = true;
        
        if ( 
            countOrders < 1 || /* Recipient has an order total greater than 1 */
                
            !result.get(0).getRecipient().getUsername().equalsIgnoreCase("Jimmy") ||
            !result.get(0).getSeller().getUsername().equalsIgnoreCase("Sally")
        ) {
            passed = false;
        }
        
        // Tidy up, remove the order that was created
        sellerInstance.deleteOrder(orderId);
        
        assertTrue(passed);
    }
    
    @Test /* T30 */
    public void testViewDriverMetrics() {
        System.out.println("T30 - viewDriverMetrics");
        
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
        System.out.println("___ getNextOrderParcelsId");
        
        SellerBean instance = new SellerBean();
        
        int result = instance.getNextOrderParcelsId();
        
        assertTrue(result > 0); // A positive next parcel ID is returned
    }

    @Test
    public void testGetDate() {
        System.out.println("___ getDate");

        SellerBean instance = new SellerBean();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();

        assertEquals(expResult, result);
    }

    @Test /* T29 */
    public void testDeleteOrder() {
        System.out.println("T29 - testDeleteOrder");
        
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

    @Test /* T31 */
    public void testViewAllOrders() {
        System.out.println("T31 - testViewAllOrders");
        
        SellerBean instance = new SellerBean();
        
        // Find all orders
        instance.viewAllOrders();
        
        int ordersFound = instance.getTotalOrders();
        
        assertTrue(ordersFound > 0); // At least one valid order was found
    }

    @Test /* T32 */
    public void testFindOrderById() {
        System.out.println("T32 - findOrderById");
        
        int orderId = 2; // We know the expected values of this order already so can test against it
        
        SellerBean instance = new SellerBean();
        
        OrderDTO result = instance.findOrderById(orderId);
        
        boolean passed = true;
        
        if ( 
            !result.getRecipient().getUsername().equalsIgnoreCase("driver") ||
            !result.getSeller().getUsername().equalsIgnoreCase("recipient") 
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
    
    @Test /* T25 */
    public void testViewUserById() {
        System.out.println("T25 - testViewUserById");
        
        int userId = 3; // We know the expected values of this user already so can test against it
        
        SellerBean instance = new SellerBean();
        
        UserDTO result = instance.findUser(userId);
        
        boolean passed = true;
        
        if ( 
            !result.getUsername().equalsIgnoreCase("seller") ||
            !result.getFirstName().equalsIgnoreCase("Anders")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }

    @Test
    public void testSetAndGetTotalOrders() {
        System.out.println("___ TotalOrders");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalOrders(expResult);
        int result = instance.getTotalOrders();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("___ Id");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRecipientId() {
        System.out.println("___ RecipientId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setRecipientId(expResult);
        int result = instance.getRecipientId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRole() {
        System.out.println("___ Role");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Driver";
        instance.setRole(expResult);
        String result = instance.getRole();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetSellerId() {
        System.out.println("___ SellerId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 3;
        instance.setSellerId(expResult);
        int result = instance.getSellerId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetParcelDetails() {
        System.out.println("___ ParcelDetails");
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO expResult = null;
        instance.setParcelDetails(expResult);
        ParcelDTO result = instance.getParcelDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalUsers() {
        System.out.println("___ TotalUsers");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalUsers(expResult);
        int result = instance.getTotalUsers();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalParcels() {
        System.out.println("___ TotalParcels");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalParcels(expResult);
        int result = instance.getTotalParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalTransactions() {
        System.out.println("___ TotalTransactions");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 0;
        instance.setTotalTransactions(expResult);
        int result = instance.getTotalTransactions();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetName() {
        System.out.println("___ Name");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "name";
        instance.setName(expResult);
        String result = instance.getName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetType() {
        System.out.println("___ Type");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "type";
        instance.setType(expResult);
        String result = instance.getType();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWeightGrams() {
        System.out.println("___ WeightGrams");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 50;
        instance.setWeightGrams(expResult);
        int result = instance.getWeightGrams();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetQuantity() {
        System.out.println("___ Quantity");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 2;
        instance.setQuantity(expResult);
        int result = instance.getQuantity();
        
        assertEquals(expResult, result);
    }

    @Test 
    public void testSetAndGetUserDetails() {
        System.out.println("___ UserDetails");
        
        SellerBean instance = new SellerBean();
        
        UserDTO expResult = new UserDTO(199, "a", "a", "TestUser", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Recipient");
        
        instance.setUserDetails(expResult);
        
        UserDTO result = instance.getUserDetails();
        
        assertEquals(expResult, result);
    }    
}
