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
        System.out.println("findRoleByUser");
        
        int userID = 3;
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Seller";
        String result = instance.findRoleByUser(userID);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testViewAllUsers() {
        System.out.println("viewAllUsers");
        
        SellerBean instance = new SellerBean();
        
        ArrayList<UserDTO> expResult = null;
        ArrayList<UserDTO> result = instance.viewAllUsers();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testDeleteParcel() {
        System.out.println("deleteParcel");
        
        int parcelId = 0;
        
        SellerBean instance = new SellerBean();
        
        String expResult = "";
        String result = instance.deleteParcel(parcelId);
        
        assertEquals(expResult, result);
    }

    /* Test:T7 */
    @Test
    public void testCreateEditParcelCreateEditOrderAddParcelToOrderValid() {
        System.out.println("createParcel (valid), editParcel (valid), createOrder (valid), editOrder (valid), addParcelToOrder");
        
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
        System.out.println("createParcel (invalid weight)");
        
        SellerBean instance = new SellerBean();
        
        instance.setName("Book");
        instance.setType("Large");
        instance.setWeightGrams(0); /* "heavy" */ ////
        instance.setSellerId(3);
        
        int currentNextParcelId = instance.getNextParcelId();
        instance.createParcel();
        int newNextParcelId = instance.getNextParcelId();
        
        assertTrue(newNextParcelId == currentNextParcelId); // No new parcel record was added and nextParcelId pointer remains the same as before the insert request
    }

    @Test
    public void testViewAllParcels() {
        System.out.println("viewAllParcels");
        
        SellerBean instance = new SellerBean();
        
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.viewAllParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testFindParcelById() {
        System.out.println("findParcelById");
        
        int parcelId = 0;
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO expResult = null;
        ParcelDTO result = instance.findParcelById(parcelId);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testFetchParcelDetails() {
        System.out.println("fetchParcelDetails");
        
        int parcelID = 0;
        
        SellerBean instance = new SellerBean();
        
        String expResult = "";
        String result = instance.fetchParcelDetails(parcelID);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTransactionByOrder() {
        System.out.println("getTransactionByOrder");
        
        int OrderID = 0;
        
        SellerBean instance = new SellerBean();
        
        ArrayList<TransactionDTO> expResult = null;
        ArrayList<TransactionDTO> result = instance.getTransactionByOrder(OrderID);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testViewDriverMetrics() {
        System.out.println("viewDriverMetrics");
        
        SellerBean instance = new SellerBean();
        
        ArrayList<MetricDTO> expResult = null;
        ArrayList<MetricDTO> result = instance.viewDriverMetrics();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextOrderParcelsId method, of class SellerBean.
     */
    @Test
    public void testGetNextOrderParcelsId() {
        System.out.println("getNextOrderParcelsId");
        SellerBean instance = new SellerBean();
        int expResult = 0;
        int result = instance.getNextOrderParcelsId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class SellerBean.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        SellerBean instance = new SellerBean();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOrder method, of class SellerBean.
     */
    @Test
    public void testDeleteOrder() {
        System.out.println("deleteOrder");
        int orderId = 0;
        SellerBean instance = new SellerBean();
        String expResult = "";
        String result = instance.deleteOrder(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewAllOrders method, of class SellerBean.
     */
    @Test
    public void testViewAllOrders() {
        System.out.println("viewAllOrders");
        SellerBean instance = new SellerBean();
        ArrayList<OrderDTO> expResult = null;
        ArrayList<OrderDTO> result = instance.viewAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchOrderDetails method, of class SellerBean.
     */
    @Test
    public void testFetchOrderDetails() {
        System.out.println("fetchOrderDetails");
        int orderID = 0;
        String role = "";
        SellerBean instance = new SellerBean();
        String expResult = "";
        String result = instance.fetchOrderDetails(orderID, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindOrderById() {
        System.out.println("findOrderById");
        
        int orderID = 0;
        
        SellerBean instance = new SellerBean();
        OrderDTO expResult = null;
        
        OrderDTO result = instance.findOrderById(orderID);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalOrders() {
        System.out.println("TotalOrders");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalOrders(expResult);
        int result = instance.getTotalOrders();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetOrderDetails() {
        System.out.println("OrderDetails");
        
        SellerBean instance = new SellerBean();
        
        //OrderDTO expResult = new OrderDTO(0, recipient, driver, seller, dateAdded, true, dateCompleted);////
        //instance.setOrderDetails(expResult);
        //OrderDTO result = instance.getOrderDetails();
        
        //assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("Id");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setId(expResult);
        int result = instance.getId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRecipientId() {
        System.out.println("RecipientId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 1;
        instance.setRecipientId(expResult);
        int result = instance.getRecipientId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetRole() {
        System.out.println("Role");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "Driver";
        instance.setRole(expResult);
        String result = instance.getRole();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetSellerId() {
        System.out.println("SellerId");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 3;
        instance.setSellerId(expResult);
        int result = instance.getSellerId();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetParcelDetails() {
        System.out.println("ParcelDetails");
        
        SellerBean instance = new SellerBean();
        
        ParcelDTO expResult = null;
        instance.setParcelDetails(expResult);
        ParcelDTO result = instance.getParcelDetails();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalUsers() {
        System.out.println("TotalUsers");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalUsers(expResult);
        int result = instance.getTotalUsers();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalParcels() {
        System.out.println("TotalParcels");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 5;
        instance.setTotalParcels(expResult);
        int result = instance.getTotalParcels();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetTotalTransactions() {
        System.out.println("TotalTransactions");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 0;
        instance.setTotalTransactions(expResult);
        int result = instance.getTotalTransactions();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetName() {
        System.out.println("Name");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "name";
        instance.setName(expResult);
        String result = instance.getName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetType() {
        System.out.println("Type");
        
        SellerBean instance = new SellerBean();
        
        String expResult = "type";
        instance.setType(expResult);
        String result = instance.getType();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWeightGrams() {
        System.out.println("WeightGrams");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 50;
        instance.setWeightGrams(expResult);
        int result = instance.getWeightGrams();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetQuantity() {
        System.out.println("Quantity");
        
        SellerBean instance = new SellerBean();
        
        int expResult = 2;
        instance.setQuantity(expResult);
        int result = instance.getQuantity();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetUserDetails() {
        System.out.println("UserDetails");
        
        SellerBean instance = new SellerBean();
        
        UserDTO expResult = null;
        instance.setUserDetails(expResult);
        UserDTO result = instance.getUserDetails();
        
        assertEquals(expResult, result);
    }    
}
