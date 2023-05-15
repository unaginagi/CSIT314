/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeromePackage;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class userAccount {
    private int uid;
    private String fullname;
    private String username;
    private int DOB_year;
    private int DOB_month;
    private int DOB_day;
    private String password;
    private String email;
    private boolean suspended;
    private int reg_date_year;
    private int reg_date_month;
    private int reg_date_day;
    

    public userAccount(int uid, String fullname, String username,  
                        int DOB_year, int DOB_month, int DOB_day, 
                        String password, String email, boolean suspended, 
                        int reg_date_year, int reg_date_month, int reg_date_day) {
        this.uid = uid;
        this.fullname = fullname;
        this.username = username;
        this.DOB_year = DOB_year;
        this.DOB_month = DOB_month;
        this.DOB_day = DOB_day;
        this.password = password;
        this.email = email;
        this.suspended = suspended;
        this.reg_date_year = reg_date_year;
        this.reg_date_month = reg_date_month;
        this.reg_date_day = reg_date_day;
    }
    
    public userAccount(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Get methods
    public int getUID() {
        return uid;
    }
    public String getFullname() {
        return fullname;
    }
    public String getUsername() {
        return username;
    }
    public int getDOB_year() {
        return DOB_year;
    }
    public int getDOB_month() {
        return DOB_month;
    }
    public int getDOB_day() {
        return DOB_day;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public boolean isSuspended() {
        return suspended;
    }
    public int getReg_date_year() {
        return reg_date_year;
    }
    public int getReg_date_month() {
        return reg_date_month;
    }
    public int getReg_date_day() {
        return reg_date_day;
    }
   
    //set method
     public void setUID(int uid) {
        this.uid = uid;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;}
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDOB_year(int DOB_year) {
        this.DOB_year = DOB_year;
    }
    public void setDOB_month(int DOB_month) {
        this.DOB_month = DOB_month;
    }
    public void setDOB_day(int DOB_day) {
        this.DOB_day = DOB_day;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
    public void setReg_date_year(int reg_date_year) {
        this.reg_date_year = reg_date_year;
    }
    public void setReg_date_month(int reg_date_month) {
        this.reg_date_month = reg_date_month;
    }
    public void setReg_date_day(int reg_date_day) {
        this.reg_date_day = reg_date_day;
    }
    
    // JDBC Credentials for testing
    
    public static String DB_URL = "jdbc:mysql://localhost:3306/cinemabooking?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static String DB_USER = "admin";
    public static String DB_PASSWORD = "password";
    
    
    public static boolean validateLogin(String username, String password) throws SQLException, Exception {
       
       String query = "SELECT COUNT(*) FROM useraccount WHERE username = ? AND password = ?";
       Class.forName("com.mysql.cj.jdbc.Driver");
       
       Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isValidLogin = false;
    
        try{
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);    
            statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        // Close the resources in the reverse order of their creation
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return isValidLogin;
    }
    
}


