package managedbean;

import dbase.DbManager;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "registration")
@RequestScoped
public class Registration implements Serializable
{

    private String name;
    private String username;
    private String password1;
    private String password2;

    public Registration()
    {
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
            
            //System.out.println(nextId);

            rs.close();
            stmt.close();
            conn.close();
            
        } catch ( SQLException sqle ) {
            sqle.printStackTrace();
        }
        
        return nextId;
    }
    
    public String register()
    {
        boolean dataOK = false;
        int id = getNextId();
        
        if (password1.equals(password2))
        {
            try
            {
                byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password1.getBytes(StandardCharsets.UTF_8));

                password1 = Base64.getEncoder().encodeToString(hash);

                try {
                    Connection conn = DbManager.getConnection();
                    
                    PreparedStatement stmt = conn.prepareStatement(""
                            + "INSERT INTO Users "
                            + "(id, firstname, lastname, username, hashedpassword, dateadded, datemodified, addresslineone, town, county, postcode, email, phone, isactive) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE, 'a', 'a', 'a', 'a', 'a', 'a', true)"
                    );
                    
                    stmt.setInt(1, id);
                    stmt.setString(2, name);
                    stmt.setString(3, name);
                    stmt.setString(4, username);
                    stmt.setString(5, password1);
                    
                    int rows = stmt.executeUpdate();

                    dataOK = rows == 1;

                    stmt.close();
                    conn.close();
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            catch (Exception e)
            {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, e.toString());
            }
        }

        if (dataOK)
        {
            return "login";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct2 - id: " + id));
            return null;
        }
    }

    public String getName()
    {
        return name;
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
    
    public void setName(String name)
    {
        this.name = name;
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

}
