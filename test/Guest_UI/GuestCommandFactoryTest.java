package Guest_UI;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuestCommandFactoryTest {
    
    public GuestCommandFactoryTest() {
    }

    @Test
    public void testCreateCommand_int_String() {
        System.out.println("___ GuestCommandFactorycreateCommand");
        
        int commandType = 0;
        
        String username = "";
        
        GuestCommand expResult = null;
        GuestCommand result = GuestCommandFactory.createCommand(commandType, username);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCommand_int_UserDTO() {
        System.out.println("___ Guest createCommand");
        
        int commandType = 0;
        
        UserDTO userDTO = null;
        
        GuestCommand expResult = null;
        GuestCommand result = GuestCommandFactory.createCommand(commandType, userDTO);
        
        assertEquals(expResult, result);
    }
}
