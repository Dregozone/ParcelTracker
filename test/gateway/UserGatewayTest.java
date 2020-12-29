package gateway;

import dto.UserDTO;
import static junit.framework.Assert.*;
import org.junit.Test;

public class UserGatewayTest {
    
    public UserGatewayTest() {
    }

    @Test
    public void testFindUserReturnsCorrectUserWhenIdIsValid() {
        System.out.println("__ findUser (valid)");
        
        UserGateway instance = new UserGateway();
        
        UserDTO expectedResult = new UserDTO(
                                        1, 
                                        "Anders", 
                                        "Learmonth", 
                                        "recipient", 
                                        "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", 
                                        "2020-01-01", 
                                        "2020-01-01", 
                                        "line 1", 
                                        "abc", 
                                        "hants", 
                                        "rg11222", 
                                        "a@b.net", 
                                        "012345", 
                                        true, 
                                        "Recipient"
        );
        
        UserDTO actualResult = instance.find(1);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testFindUserReturnsNullWhenIdIsInvalid() {
        System.out.println("__ findUser (invalid)");
        
        UserGateway instance = new UserGateway();
        UserDTO actualResult = instance.find(-1);
        
        assertNull(actualResult);
    }
}
