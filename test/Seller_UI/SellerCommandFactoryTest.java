package Seller_UI;

import dto.OrderDTO;
import dto.ParcelDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class SellerCommandFactoryTest {
    
    public SellerCommandFactoryTest() {
    }

    @Test
    public void testCreateCommand_int() {
        System.out.println("__ createCommand");
        
        int commandType = 0;
        
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCommand_int_int() {
        System.out.println("__ createCommand");
        
        int commandType = 0;
        int id = 0;
        
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, id);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCommand_int_OrderDTO() {
        System.out.println("__ createCommand");
        
        int commandType = 0;
        
        OrderDTO orderDTO = null;
        
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, orderDTO);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCommand_int_ParcelDTO() {
        System.out.println("__ createCommand");
        
        int commandType = 0;
        
        ParcelDTO parcelDTO = null;
        
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, parcelDTO);
        
        assertEquals(expResult, result);
    }
}
