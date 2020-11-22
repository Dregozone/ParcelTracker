package managedbean;

import userUI.UserCommandFactory;
import dto.CustomerDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "customer")
@SessionScoped
public class CustomerBean implements Serializable
{

    private CustomerDTO customerDetails = null;
    private int totalCustomers = 0;

    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        ArrayList<CustomerDTO> customerSummaries
                = (ArrayList<CustomerDTO>) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.GET_CUSTOMER_SUMMARIES)
                        .execute();

        totalCustomers = customerSummaries.size();

        return customerSummaries;
    }

    public String fetchCustomerDetails(int custID)
    {
        customerDetails
                = (CustomerDTO) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.FIND_CUSTOMER_BY_ID,
                                custID)
                        .execute();

        return "viewCustomer";
    }

    public CustomerDTO getCustomerDetails()
    {
        return customerDetails;
    }

    public int getTotalCustomers()
    {
        return totalCustomers;
    }

    public void setCustomerDetails(CustomerDTO customerDetails)
    {
        this.customerDetails = customerDetails;
    }
}
