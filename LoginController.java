package JeromePackage;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController{
    //String username;
    //String password;
    //private final userAccount ac = new userAccount(username,password);
    
    public static boolean login(String username, String password) throws SQLException, Exception{
        boolean isLoginSuccessful = false;
        isLoginSuccessful = userAccount.validateLogin(username,password);
        return isLoginSuccessful;
    }
}


