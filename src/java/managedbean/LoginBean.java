package managedbean;

import Guest_UI.GuestCommandFactory;
import dto.UserDTO;
import manager.DbManager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "loginBean")
@SessionScoped //Session to allow user to remain logged in for their session
public class LoginBean implements Serializable
{
    private int id;
    private String username;
    private String password;
    private String role;
    private boolean credentialsOK = false;

    public LoginBean()
    {
    }

    private String hashPassword(String password) {
        
        String hashed = password; ////
        
        return hashed;
    }
    
    public String checkCredentials()
    {
        credentialsOK = false;
        
        try {
            Connection conn = DbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            credentialsOK = rs.next() && rs.getString("hashedpassword").equals(password);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (credentialsOK)
        {

            return userPage();
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return null;
        }
    }
    
    public String userPage() {
        
        return findUserRole() + "_UI";
    }

    private String findUserRole() {
        
        UserDTO userDetails = null;
        
        userDetails
                = (UserDTO) GuestCommandFactory
                        .createCommand(GuestCommandFactory.VIEW_USER_BY_USERNAME,
                                username)
                        .execute();
        
        this.id = userDetails.getId();
        this.role = userDetails.getRole();

        return this.role;
    }
    
    private void clearCredentials()
    {
        this.username = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public String logout() {
        clearCredentials();
        
        return "Login_UI";
    }
    
    public boolean credentialsAreOK()
    {
        return credentialsOK;
    }

    public int getId()
    {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        try
        {
            byte[] hash
                    = MessageDigest.getInstance("SHA-256")
                            .digest(password.getBytes(StandardCharsets.UTF_8));

            this.password
                    = Base64.getEncoder().encodeToString(hash);

        }
        catch (NoSuchAlgorithmException ex)
        {
            this.password = "";
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
