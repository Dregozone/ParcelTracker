package userUI;

import dto.CustomerDTO;
import dto.UserDTO;

public class UserCommandFactory
{

    public static final int GET_CUSTOMER_SUMMARIES = 1;
    public static final int FIND_CUSTOMER_BY_ID = 2;
    public static final int ADD_CUSTOMER = 3;
    public static final int GET_USER_SUMMARIES = 4;
    public static final int FIND_USER_BY_ID = 5;

    public static UserCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_CUSTOMER_SUMMARIES:
                return new GetCustomerSumariesCommand();
            case GET_USER_SUMMARIES:
                return new GetUserSumariesCommand();
            default:
                return null;
        }
    }

    public static UserCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case FIND_CUSTOMER_BY_ID:
                return new FindCustomerCommand(id);
            case FIND_USER_BY_ID:
                return new FindUserCommand(id);
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
