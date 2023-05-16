package userAccount;

import java.sql.*;

public class userAccount
{
    public int UID;
    public String name;
    public Date DOB;
    public String user;
    public String password;
    public int phoneNo;
    public String email;
    public String address;
    public boolean suspended;
    
    public userAccount ()  
    {

    }

    public userAccount (int UID, String name, Date DOB, String user, String password, 
                        int phoneNo, String email, String address, boolean suspended)
    {
        this.UID = UID;
        this.name = name;
        this.DOB = DOB;
        this.user = user;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.suspended = suspended;
    }

    public boolean addUserAccount (String name, Date DOB, String user, String password,
                               int phoneNo, String email, String address) 
                                throws SQLException, ClassNotFoundException
    {
        try
        {
            
            String URL = "jdbc:mysql://localhost/";
            Class.forName ("com.mysql.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL + "csit314",  "", "");
    
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("INSERT INTO userAccount (name, DOB, user, password," +
                                       "phoneNo, email, address) values (" + name + ", " + DOB
                                        + ", " + user + ", " + password + ", " + phoneNo + 
                                        "," + email + ", " + address + ")");
            ResultSet rset = myStmt.executeQuery();
	    return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public userAccount getUserAccount (int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            
            String URL = "jdbc:mysql://localhost/";
            Class.forName ("com.mysql.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL + "csit314",  "", "");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Select * from userAccount where UID = " + UID);
            ResultSet rset = myStmt.executeQuery();
            String name1 = rset.getString("name");
            Date DOB1 = rset.getDate("DOB");
            String user1 = rset.getString("user");
            String password1 = rset.getString("password");
            int phoneNo1 = rset.getInt("phoneNo");
            String emai1l = rset.getString("email");
            String address1 = rset.getString("address");
            int suspended1 = rset.getInt("suspended");
            boolean suspended2;
            if (suspended1 == 1)
            {
                suspended2 = true;
            }
            else
            {
                suspended2 = false;
            }

            userAccount output = new userAccount (UID, name1, DOB1, user1, password1,
                                                 phoneNo1, emai1l, address1, suspended2);
      
            return output;
        }
        catch (SQLException e )
        {
            return null;
        }
    }

  
    public boolean updatingUserAccount (int UID, String name, Date DOB, String user, String password,
                                    int phoneNo, String email, String address) 
                                    throws SQLException, ClassNotFoundException
    {
        try
        {
            String URL = "jdbc:mysql://localhost/";
            Class.forName ("com.mysql.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL + "csit314",  "", "");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Update userAccount SET name = " + name + ", DOB = " + DOB
                                              + ", user = " + user + ", password = " + password 
                                              + ", phoneNo = " + phoneNo + ", email = " + email 
                                              + ", address = " + address + "WHERE UID = " + UID);
            ResultSet rset = myStmt.executeQuery();
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public boolean suspendUserAccount (int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            String URL = "jdbc:mysql://localhost/";
            Class.forName ("com.mysql.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL + "csit314",  "", "");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Update userAccount SET suspended = 1 WHERE UID = " + UID);
            ResultSet rset = myStmt.executeQuery();
            
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public userAccount[] getUserAccount (int UID, String name, Date DOB, String user, String password,
                                        int phoneNo, String email, String address)
                                         throws SQLException, ClassNotFoundException
    {
        try
        {
            String URL = "jdbc:mysql://localhost/";
            Class.forName ("com.mysql.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL + "csit314",  "", "");
            
            userAccount [] searched;
            searched = new userAccount [0];
            PreparedStatement myStmt;
            String statement;
            if (UID == 0)
            {
                statement =  "Select * from userAccount where UID LIKE %" + UID +"%";
            }
            else
            {
                statement =  "Select * from userAccount where UID is NOT NULL";
            }
            
            if (name == null)
            {
                statement += "and name LIKE %" + name + "%";
            }
            
            if (DOB == null)
            {
                statement += "and DOB LIKE %" + DOB + "%";
            }
            
            if (user == null)
            {
                statement += "and user LIKE %" + user +"%";
            }
            
            if (password == null)
            {
                statement += "and password LIKE %" + password +"%";
            }
            
            if (phoneNo == 0)
            {
                statement += "and phoneNo LIKE %" + phoneNo+"%";
            }
            
            if (email == null)
            {
                statement += "and email LIKE %" + email +"%";
            }
            
            if (address == null)
            {
                statement += "and address LIKE %" + address+ "%";
            }
            
            if (UID == -1)
            {
                statement = "Select * from userAccount;";
            }
            
            
            myStmt = myCon.prepareStatement(statement);
            
            ResultSet rset = myStmt.executeQuery();

            while (rset.next())
            {
                int UID1 = rset.getInt("UID");
                String name1 = rset.getString("name");
                Date DOB1 = rset.getDate("DOB");
                String user1 = rset.getString("user");
                String password1 = rset.getString("password");
                int phoneNo1 = rset.getInt("phoneNo");
                String email1 = rset.getString("email");
                String address1 = rset.getString("address");
                int suspended1 = rset.getInt("suspended");
                boolean suspended2;
                if (suspended1 != 1)
                {
                    suspended2 = false;
                }
                else
                {
                    suspended2 = true;
                }
                userAccount output = new userAccount (UID1, name1, DOB1, user1, password1,
                                                    phoneNo1, email1, address1, suspended2);
                searched = addX(searched.length, searched, output);
            }
            return searched;
        }
        catch (SQLException e )
        {
            return null;
        }
    }

    // Function to add x in arr
   public static userAccount[] addX(int n, userAccount arr[], userAccount x)
   {
       int i;
   
       // create a new array of size n+1
       userAccount newarr[] = new userAccount[n + 1];
   
       // insert the elements from
       // the old array into the new array
       // insert all elements till n
       // then insert x at n+1
       for (i = 0; i < n; i++)
           newarr[i] = arr[i];
   
       newarr[n] = x;
   
       return newarr;
   }
}