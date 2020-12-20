package dto;

import java.io.Serializable;
import java.util.Objects;

public class ParcelDTO implements Serializable
{
    private final int id;
    private final String name;
    private final String type;
    private final int weightGrams;
    private final UserDTO seller;
    private final String dateAdded;
    private final String dateModified;
    private int quantityInOrder;

    public ParcelDTO(int id, String name, String type, int weightGrams, UserDTO seller, String dateAdded, String dateModified, int quantityInOrder)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weightGrams = weightGrams;
        this.seller = seller;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.quantityInOrder = quantityInOrder;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + this.weightGrams;
        hash = 29 * hash + Objects.hashCode(this.seller);
        hash = 29 * hash + Objects.hashCode(this.dateAdded);
        hash = 29 * hash + Objects.hashCode(this.dateModified);
        hash = 29 * hash + this.quantityInOrder;
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
        final ParcelDTO other = (ParcelDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.weightGrams != other.weightGrams) {
            return false;
        }
        if (this.quantityInOrder != other.quantityInOrder) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.dateModified, other.dateModified)) {
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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public UserDTO getSeller() {
        return seller;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }
    
    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }
}
