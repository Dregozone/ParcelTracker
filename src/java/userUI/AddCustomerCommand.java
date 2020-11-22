package userUI;

import dto.CustomerDTO;
import manager.CustomerManager;

public class AddCustomerCommand implements UserCommand
{

    private final CustomerDTO customerDTO;
    private final CustomerManager custMgr;

    public AddCustomerCommand(CustomerDTO customerDTO)
    {
        this.customerDTO = customerDTO;
        custMgr = new CustomerManager();
    }

    @Override
    public Object execute()
    {
        if (custMgr.insertCustomer(customerDTO))
        {
            return custMgr
                    .findCustomer(
                            customerDTO.getName(),
                            customerDTO.getAddressLine1(),
                            customerDTO.getZipCode());
        }
        return null;
    }

}
