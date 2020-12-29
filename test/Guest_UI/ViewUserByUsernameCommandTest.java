package Guest_UI;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewUserByUsernameCommandTest {
    
    public ViewUserByUsernameCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ GuestViewUserByUsernameCommand execute");

        ViewUserByUsernameCommand instance = new ViewUserByUsernameCommand("driver");

        String expResult = "driver";
        Object result = instance.execute();

        assertEquals(expResult, ((UserDTO)result).getUsername() );
    }    
}
