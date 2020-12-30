package Seller_UI;

import dto.MetricDTO;
import java.util.ArrayList;
import managedbean.DriverBean;
import managedbean.SellerBean;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ViewDriverMetricsCommandTest {
    
    public ViewDriverMetricsCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewDriverMetrics execute");

        // Create order so at least one order exists for this metric generation
        SellerBean sellerInstance = new SellerBean();
        int orderId = sellerInstance.getNextOrderId();
        sellerInstance.setRecipientId(1);
        sellerInstance.setSellerId(3);
        sellerInstance.createOrder();
        
        // Add at least one completed order to allow valid metrics to be attained
        DriverBean driverInstance = new DriverBean();
        driverInstance.addTransaction(orderId, "Picked up", 2); // Assigns "driver" to this order
        driverInstance.addTransaction(orderId, "Dropped off", 2); // Provides completed date for this order
        
        ViewDriverMetricsCommand instance = new ViewDriverMetricsCommand();

        Object results = instance.execute();

        ArrayList<MetricDTO> metrics = (ArrayList<MetricDTO>)results;
        
        MetricDTO metric = metrics.get(0);
        
        /*
        System.out.println( metric.getName() );
        System.out.println( metric.getDaysToComplete() );
        System.out.println( metric.getDeliveryCount() );
        */
        
        boolean passed = true;
        
        if (
            !metric.getName().equalsIgnoreCase("driver") ||
            metric.getDaysToComplete() != 0 /*||*/
            /* metric.getDeliveryCount() != 3 */ // Omit this count as it could change over time if wanting to test later
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
