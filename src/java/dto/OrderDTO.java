package dto;

import java.io.Serializable;
import java.util.Objects;

public class OrderDTO implements Serializable
{
    private final int id;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.recipient);
        hash = 73 * hash + Objects.hashCode(this.driver);
        hash = 73 * hash + Objects.hashCode(this.seller);
        hash = 73 * hash + Objects.hashCode(this.dateAdded);
        hash = 73 * hash + (this.isComplete ? 1 : 0);
        hash = 73 * hash + Objects.hashCode(this.dateCompleted);
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
        final OrderDTO other = (OrderDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isComplete != other.isComplete) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.dateCompleted, other.dateCompleted)) {
            return false;
        }
        if (!Objects.equals(this.recipient, other.recipient)) {
            return false;
        }
        if (!Objects.equals(this.driver, other.driver)) {
            return false;
        }
        if (!Objects.equals(this.seller, other.seller)) {
            return false;
        }
        return true;
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
