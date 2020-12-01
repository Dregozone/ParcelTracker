package managedbean;

import userUI.UserCommandFactory;
import dto.CustomerDTO;
import dto.DiscountDTO;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "addCustomer")
@RequestScoped
public class AddCustomerBean
{

    @Inject
    CustomerBean customerBean;

    private int id;
    private String discountCode;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;

    public AddCustomerBean()
    {
    }

    /*
    public String addCustomer()
    {
        CustomerDTO newCust
                = new CustomerDTO(
                        id,
                        new DiscountDTO(discountCode, 0),
                        name,
                        addressLine1,
                        addressLine2,
                        city,
                        state,
                        zipCode);

        CustomerDTO insertedCust
                = (CustomerDTO) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.ADD_CUSTOMER,
                                newCust)
                        .execute();

        customerBean.setCustomerDetails(insertedCust);

        return "viewCustomer";
    }
*/

    public int getId()
    {
        return id;
    }

    public String getDiscountCode()
    {
        return discountCode;
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

    public void setId(int id)
    {
        this.id = id;
    }

    public void setDiscountCode(String discountCode)
    {
        this.discountCode = discountCode;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

}
