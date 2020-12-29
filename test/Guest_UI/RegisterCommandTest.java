package Guest_UI;

import dto.UserDTO;
import managedbean.RegisterBean;
import managedbean.SellerBean;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterCommandTest {
    
    public RegisterCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ RecipientRegisterCommand execute");

        RegisterBean registerInstance = new RegisterBean();
        
        int currentNextUserId = registerInstance.getNextId();
        
        UserDTO userDetails = new UserDTO(currentNextUserId, "a", "a", "TestUser", "123", "a", "a", "a", "a", "a", "a", "a", "a", true, "Recipient");        
        
        // Perform registration
        RegisterCommand instance = new RegisterCommand(userDetails);
        instance.execute();

        // Find new next user Id
        int newNextUserId = registerInstance.getNextId();
        
        assertTrue( newNextUserId > currentNextUserId ); // Confirms that a new user was registered
    }    
}
