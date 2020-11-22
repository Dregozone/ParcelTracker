package userUI;

import dto.CustomerDTO;

public class UserCommandFactory
{

    public static final int GET_CUSTOMER_SUMMARIES = 1;
    public static final int FIND_CUSTOMER_BY_ID = 2;
    public static final int ADD_CUSTOMER = 3;

    public static UserCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_CUSTOMER_SUMMARIES:
                return new GetCustomerSumariesCommand();
            default:
                return null;
        }
    }

    public static UserCommand createCommand(int commandType, int custId)
    {
        switch (commandType)
        {
            case FIND_CUSTOMER_BY_ID:
                return new FindCustomerCommand(custId);
            default:
                return null;
        }
    }

    public static UserCommand createCommand(int commandType, CustomerDTO customerDTO)
    {
        switch (commandType)
        {
            case ADD_CUSTOMER:
                return new AddCustomerCommand(customerDTO);
            default:
                return null;
        }
    }
}
