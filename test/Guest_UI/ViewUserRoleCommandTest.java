package Guest_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class ViewUserRoleCommandTest {
    
    public ViewUserRoleCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ GuestViewUserRoleCommand execute");
        
        ViewUserRoleCommand instance = new ViewUserRoleCommand("driver");
        
        String expResult = "Driver";
        String result = instance.execute();
        
        assertEquals(expResult, result);
    }    
}
