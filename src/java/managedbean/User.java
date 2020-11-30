package managedbean;

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

@Named(value = "user")
@SessionScoped
public class User implements Serializable
{

    private int id;
    private String username;
    private String password;
    private boolean credentialsOK = false;

    public User()
    {
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct1"));
            return null;
        }
    }
    
    public String userPage() {
        
        return findUserRole() + "_UI";
    }

    private String findUserRole() {
        
        String role = "Recipient"; // Set default
        
        try {
            Connection conn = DbManager.getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(""
                    + "SELECT R.name AS ROLE "
                    + "FROM UserRoles UR "
                    + "INNER JOIN Users U ON UR.UserID = U.ID "
                    + "INNER JOIN Roles R ON UR.RoleID = R.ID "
                    + "WHERE U.username = ?"
            );
            
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();

            if ( rs.next() ) {
                role = rs.getString("ROLE");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return role;
    }
    
    private void clearCredentials()
    {
        this.username = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public String logOff()
    {
        clearCredentials();
        return "login";
    }
    ////
    public String logout() {
        clearCredentials();
        
        return "login";
    }
    
    public boolean credentialsAreOK()
    {
        return credentialsOK;
    }

    public int getId()
    {
        return id;
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
