package Guest_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class GuestCommandTest {
    
    public GuestCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("execute");
        
        GuestCommand instance = new GuestCommandImpl();
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }

    public class GuestCommandImpl implements GuestCommand {

        public Object execute() {
            return null;
        }
    }
}
