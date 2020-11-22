package dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable
{

    private final int id;
    private final DiscountDTO discount;
    private final String name;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String zipCode;

    public CustomerDTO(int id, DiscountDTO discount, String name, String addressLine1, String addressLine2, String city, String state, String zipCode)
    {
        this.id = id;
        this.discount = discount;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public int getId()
    {
        return id;
    }

    public DiscountDTO getDiscount()
    {
        return discount;
    }

    public String getName()
    {
        return name;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZipCode()
    {
        return zipCode;
    }
}
