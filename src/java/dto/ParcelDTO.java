package dto;

import java.io.Serializable;

public class ParcelDTO implements Serializable
{
    private final int id;
    private final String name;
    private final String type;
    private final int weightGrams;
    private final UserDTO seller;
    private final String dateAdded;
    private final String dateModified;
    private final int timesSold;
    private int quantityInOrder;

    public ParcelDTO(int id, String name, String type, int weightGrams, UserDTO seller, String dateAdded, String dateModified, int timesSold, int quantityInOrder)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weightGrams = weightGrams;
        this.seller = seller;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.timesSold = timesSold; 
        this.quantityInOrder = quantityInOrder;
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

    public int getTimesSold() {
        return timesSold;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }
    
    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }
}
