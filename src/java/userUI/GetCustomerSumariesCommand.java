package userUI;

import manager.CustomerManager;

public class GetCustomerSumariesCommand implements UserCommand
{

    private final CustomerManager custMgr;

    public GetCustomerSumariesCommand()
    {
        custMgr = new CustomerManager();
    }

    @Override
    public Object execute()
    {
        return custMgr.getCustomerSummaries();
    }

}
