package Seller_UI;

import dto.UserDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewAllUsersCommandTest {
    
    public ViewAllUsersCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewAllUsers execute");

        ViewAllUsersCommand instance = new ViewAllUsersCommand();

        Object result = instance.execute();
        
        int countUsers = ((ArrayList<UserDTO>)result).size();

        assertTrue( countUsers > 0 ); // There is at least 1 valid user
    }    
}
