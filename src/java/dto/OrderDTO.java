package dto;

import java.io.Serializable;

public class OrderDTO implements Serializable
{
    private final int id;
    //private final DiscountDTO discount;
    private final UserDTO recipient;
    private final UserDTO driver;
    private final UserDTO seller;
    private final String dateAdded;
    private final boolean isComplete;
    private final String dateCompleted;

    public OrderDTO(int id, UserDTO recipient, UserDTO driver, UserDTO seller, String dateAdded, boolean isComplete, String dateCompleted)
    {
        this.id = id;
        this.recipient = recipient;
        this.driver = driver;
        this.seller = seller;
        this.dateAdded = dateAdded;
        this.isComplete = isComplete;
        this.dateCompleted = dateCompleted;
    }

    public int getId()
    {
        return id;
    }

    public UserDTO getRecipient() {
        return recipient;
    }

    public UserDTO getDriver() {
        return driver;
    }

    public UserDTO getSeller() {
        return seller;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }
}
