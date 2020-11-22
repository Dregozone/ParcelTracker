package userUI;

import manager.CustomerManager;

public class FindCustomerCommand implements UserCommand
{

    private final int custId;
    private final CustomerManager custMgr;

    public FindCustomerCommand(int custId)
    {
        this.custId = custId;
        custMgr = new CustomerManager();

    }

    @Override
    public Object execute()
    {
        return custMgr.findCustomer(custId);
    }

}
