package dto;

import java.io.Serializable;
import java.util.Objects;

public class TransactionDTO implements Serializable
{
    private final int id;
    private final int orderId;
    private final String name;
    private final UserDTO addedBy;
    private final String dateAdded;

    public TransactionDTO(int id, int orderId, String name, UserDTO addedBy, String dateAdded)
    {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
        this.addedBy = addedBy;
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.orderId;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.addedBy);
        hash = 23 * hash + Objects.hashCode(this.dateAdded);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionDTO other = (TransactionDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.addedBy, other.addedBy)) {
            return false;
        }
        return true;
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

    public String getDateAdded() {
        return dateAdded;
    }
    
}
