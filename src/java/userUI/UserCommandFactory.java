package userUI;

public class UserCommandFactory
{
    public static final int GET_USER_SUMMARIES = 4;
    public static final int FIND_USER_BY_ID = 5;
    public static final int GET_ORDER_SUMMARIES = 6;
    public static final int FIND_ORDER_BY_ID = 7;
    public static final int GET_ORDER_SUMMARIES_BY_USER = 8;
    public static final int GET_PARCEL_SUMMARIES_BY_ORDER = 9;
    public static final int GET_TRANSACTION_SUMMARIES_BY_ORDER = 10;

    public static UserCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_USER_SUMMARIES:
                return new GetUserSumariesCommand();
            case GET_ORDER_SUMMARIES:
                return new GetOrderSumariesCommand();
            default:
                return null;
        }
    }

    public static UserCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case FIND_USER_BY_ID:
                return new FindUserCommand(id);
            case FIND_ORDER_BY_ID:
                return new FindOrderCommand(id);
            case GET_ORDER_SUMMARIES_BY_USER:
                return new GetOrderSumariesByUserCommand(id);
            case GET_PARCEL_SUMMARIES_BY_ORDER: 
                return new GetParcelSumariesByOrderCommand(id);
            case GET_TRANSACTION_SUMMARIES_BY_ORDER: 
                return new GetTransactionSumariesByOrderCommand(id);
            default:
                return null;
        }
    }
}
