package driverUI;

public class DriverCommandFactory
{
    public static final int VIEW_ALL_DELIVERIES = 6;
    
    public static DriverCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case VIEW_ALL_DELIVERIES:
                return new ViewAllDeliveriesCommand();
            default:
                return null;
        }
    }
}
