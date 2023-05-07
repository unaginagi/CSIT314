
package Control;

import userAccount.userAccount;
import java.sql.Date;  
import java.sql.SQLException;

public class userAccountControl
{
    public userAccountControl ()  
    {
        
    }

    public boolean createUserAccount (String name, Date DOB, String user, String password,
                                        int phoneNo, String email, String address) throws SQLException, ClassNotFoundException
    {
        
        userAccount ua1 = new userAccount ();
        boolean output = ua1.addUserAccount (name, DOB, user, password, phoneNo, email, address);
        return output;
    }

    public userAccount retrieveUserAccountInfo (int UID) throws SQLException, ClassNotFoundException
    {

        userAccount ua1 = new userAccount ();

        userAccount output = ua1.getUserAccount (UID);
    
        return output;
    }

    public boolean UpdateUserAccount (int UID, String name, Date DOB, String user, String password,
                                    int phoneNo, String email, String address) throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        boolean output = ua1.updatingUserAccount (UID, name, DOB, user, password,
                                        phoneNo, email, address);

        return output;
    }

    public boolean LockUserAccount (int UID)throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        boolean output = ua1.suspendUserAccount (UID);
        return output;
    }

    public userAccount[] getUserAccount (Integer UID, String name, Date DOB, String user, String password,
                                        Integer phoneNo, String email, String address)throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        userAccount [] output = ua1.getUserAccount (UID, name, DOB, user, password,
                                        phoneNo, email, address);
        return output;
        
    }
}