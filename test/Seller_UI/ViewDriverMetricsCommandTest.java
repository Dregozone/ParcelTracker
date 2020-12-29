package Seller_UI;

import dto.MetricDTO;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ViewDriverMetricsCommandTest {
    
    public ViewDriverMetricsCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewDriverMetrics execute");

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
            !metric.getName().equalsIgnoreCase("None") ||
            metric.getDaysToComplete() != 2 /*||*/
            /* metric.getDeliveryCount() != 3 */ // Omit this count as it could change over time if wanting to test later
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
