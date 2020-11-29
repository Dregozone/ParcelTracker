package managedbean;

import dbase.DbManager;
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
    private String name;
    private String username;
    private String password;
    private boolean credentialsOK = false;

    public User()
    {
    }

    public String checkCredentials()
    {
        credentialsOK = false;
        
        try
        {
            try {
                Connection conn = DbManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                //credentialsOK = rs.next() && rs.getString("pwd").equals(password);
                credentialsOK = rs.next() && rs.getString("hashedpassword").equals(password);

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, e.toString());
        }

        if (credentialsOK)
        {
            return "index";
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct1"));
            return null;
        }

    }

    private void clearCredentials()
    {
        this.name = "";
        this.username = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
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

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String logOff()
    {
        clearCredentials();
        return "login";
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
