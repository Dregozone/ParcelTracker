package managedbean;

import Guest_UI.GuestCommandFactory;
import dto.UserDTO;
import manager.DbManager;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable
{
    private String firstName;
    private String lastName;
    private String username;
    private String password1;
    private String password2;
    
    private String addressLineOne;
    private String town;
    private String county;
    private String postcode;
    private String email;
    private String phone;
    
    private UserDTO userDetails = null;

    public RegisterBean()
    {
    }

    public java.sql.Date getDate() {
        
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        return sqlDate;
    }
    
    public int getNextId() {

        int nextId = 0;
        
        try {
            
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT ID+1 AS ID FROM Users ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

            ResultSet rs = stmt.executeQuery();

            if ( rs.next() ) {
                nextId = rs.getInt("ID");
            }

            rs.close();
            stmt.close();
            conn.close();
            
        } catch ( SQLException sqle ) {
            sqle.printStackTrace();
        }
        
        return nextId;
    }
    
    private boolean validateInputs() {
        
        boolean isValid = true;
        
        if ( !password1.equals(password2) ) {
            isValid = false;
        }
        
        return isValid;
    }
    
    private String hashPassword(String unhashed) {
        
        String hashed = unhashed; ////todo
        
        return hashed;
    }
    
    public String register()
    {
        boolean dataOK = false;
        int id = getNextId();
        
        if ( validateInputs() )
        {            
            try
            {
                byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password1.getBytes(StandardCharsets.UTF_8));

                password1 = Base64.getEncoder().encodeToString(hash);
                
                UserDTO newUser = new UserDTO(
                                    id,
                                    firstName,
                                    lastName, 
                                    username, 
                                    password1, 
                                    "",
                                    "",
                                    addressLineOne,
                                    town,
                                    county,
                                    postcode, 
                                    email, 
                                    phone,
                                    true,
                                    "Recipient"
                );

                UserDTO insertedUser 
                        = (UserDTO) GuestCommandFactory
                                .createCommand(GuestCommandFactory.CREATE_USER,
                                        newUser)
                                .execute();

                userDetails = insertedUser;

                dataOK = true;
            }
            catch (Exception e)
            {
                Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, e.toString());
            }
        }

        if ( dataOK )
        {
            return "Login_UI";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration failed checks: Please check values entered"));
            return null;
        }
    }

    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }

    public String getPassword1()
    {
        return password1;
    }

    public String getPassword2()
    {
        return password2;
    }

    public String getUsername()
    {
        return username;
    }
    
    public void setFirstName(String name)
    {
        this.firstName = name;
    }
    
    public void setLastName(String name)
    {
        this.lastName = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword1(String password1)
    {
        this.password1 = password1;
    }

    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
