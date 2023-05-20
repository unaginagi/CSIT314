package Controller;
import Entity.userAccount;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController{
    //String username;
    //String password;
    //private final userAccount ac = new userAccount(username,password);
    
    public static int login(String username, String password) throws SQLException, Exception{
        int isLoginSuccessful = -1;
        isLoginSuccessful = userAccount.validateLogin(username,password);
        return isLoginSuccessful;
    }
}


