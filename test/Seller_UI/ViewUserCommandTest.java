package Seller_UI;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewUserCommandTest {
    
    public ViewUserCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ Seller ViewUserCommand execute");
        
        int userId = 1;
        
        ViewUserCommand instance = new ViewUserCommand(userId);
        
        String expResult = "recipient";
        Object result = instance.execute();
        
        UserDTO user = (UserDTO)result;
        
        assertEquals( expResult, user.getUsername() );
    }    
}
