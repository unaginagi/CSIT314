package Control;

import userAccount.userAccount;
import java.sql.Date;  
import java.sql.SQLException;

public class createUserAccountControl
{
    public createUserAccountControl ()  
    {
        
    }

    public boolean createUserAccount (String name, Date DOB, String user, String password,
                                        int phoneNo, String email, String address) throws SQLException, ClassNotFoundException
    {
        
        userAccount ua1 = new userAccount ();
        boolean output = ua1.addUserAccount (name, DOB, user, password, phoneNo, email, address);
        return output;
    }
}