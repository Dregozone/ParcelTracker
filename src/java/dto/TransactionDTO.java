package dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable
{
    private final int id;
    private final int orderId;
    private final String name;
    private final UserDTO addedBy;

    public TransactionDTO(int id, int orderId, String name, UserDTO addedBy)
    {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
        this.addedBy = addedBy;
    }

    public int getId()
    {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public UserDTO getAddedBy() {
        return addedBy;
    }
}
