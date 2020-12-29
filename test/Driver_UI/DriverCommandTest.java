package Driver_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class DriverCommandTest {
    
    public DriverCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ DriverCommand execute");
        
        DriverCommand instance = new DriverCommandImpl();
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }

    public class DriverCommandImpl implements DriverCommand {

        public Object execute() {
            return null;
        }
    }
}
