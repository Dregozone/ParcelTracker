package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetricDTOTest {
    
    public MetricDTOTest() {
    }

    @Test
    public void testGetValues() {
        System.out.println("MetricDTO: Get values");
        
        MetricDTO instance = new MetricDTO("Anders", 2, 3);
        
        boolean passed = true;
        
        if (
            !instance.getName().equalsIgnoreCase("Anders") ||
            instance.getDeliveryCount() != 3 ||
            instance.getDaysToComplete() != 2
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
