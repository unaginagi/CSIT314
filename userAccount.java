package userAccount;

import java.sql.*;

public class userAccount
{
    public int UID;
    public String name;
    public Date DOB;
    public String user;
    public String password;
    public String phoneNo;
    public String email;
    public String address;
    public boolean suspended;
    public int profileID;
    public String URL = "jdbc:mysql://localhost:3306/csit314?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    public userAccount ()  
    {

    }

    public userAccount (int UID, String name, Date DOB, String user, String password, 
                        String phoneNo, String email, String address, boolean suspended, int profileID)
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
        this.profileID = profileID;
    }
    
    public boolean addUserAccount (String name, Date DOB, String user, String password,
                               String phoneNo, String email, String address,int profileID) 
                                throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
    
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("INSERT INTO userAccount (name, DOB, user, password," +
                                       "phoneNo, email, address, profileID) values ('" + name + "', '" + DOB
                                        + "', '" + user + "', '" + password + "', '" + phoneNo + 
                                        "','" + email + "', '" + address + "', '" + profileID + ")");
            int rset = myStmt.executeUpdate();
	    return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }
    
    public boolean checkUser (String user) throws SQLException, ClassNotFoundException
    {
        
        Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
        PreparedStatement myStmt;
        myStmt = myCon.prepareStatement("Select user from userAccount");
        ResultSet rset = myStmt.executeQuery();
        while (rset.next())
        {
            String user1 = rset.getString("user");
            if (user1.equals(user))
            {
                return true;
            }
        }
        
        return false;
    }

    public userAccount getUserAccount (int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Select * from useraccount where UID = " + UID);
            ResultSet rset = myStmt.executeQuery();
            String name1 ="";
            Date DOB1 = null;
            String user1 = "";
            String password1 = "";
            String phoneNo1 = "";
            String emai1l = "";
            String address1 = "";
            int profileID1 = 0;
            boolean suspended2 = false;
            
            if (rset.next()) 
            {
                name1 = rset.getString("name");
                DOB1 = rset.getDate("DOB");
                user1 = rset.getString("user");
                password1 = rset.getString("password");
                phoneNo1 = rset.getString("phoneNo");
                emai1l = rset.getString("email");
                address1 = rset.getString("address");
                int suspended1 = rset.getInt("suspended");
                if (suspended1 == 1)
                {
                    suspended2 = true;
                }
                else
                {
                    suspended2 = false;
                }
                profileID1 = rset.getInt("profileID");
            }
            
            if ("".equals(name1))
            {
                return null;
            }

            userAccount output = new userAccount (UID, name1, DOB1, user1, password1,
                                                 phoneNo1, emai1l, address1, suspended2, profileID1);
      
            return output;
        }
        catch (SQLException e )
        {
            e.printStackTrace(System.out);
            return null;
        }
    }

  
    public boolean updatingUserAccount (int UID, String name, Date DOB, String user, String password,
                                    String phoneNo, String email, String address) 
                                    throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Update userAccount SET name = '" + name + "', DOB = '" + DOB
                                              + "', user = '" + user + "', password = '" + password 
                                              + "', phoneNo = '" + phoneNo + "', email = '" + email 
                                              + "', address = '" + address + "' WHERE UID = " + UID);
            int rset = myStmt.executeUpdate();
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
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Update userAccount SET suspended = 1 WHERE UID = " + UID);
            int rset = myStmt.executeUpdate();
            
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public userAccount[] getUserAccount (int UID, String name, Date DOB, String user, String password,
                                        String phoneNo, String email, String address, int profileID)
                                         throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");
            
            userAccount [] searched;
            searched = new userAccount [0];
            PreparedStatement myStmt;
            String statement;
            
            if (UID == -1)
            {
                statement = "Select * from userAccount;";
            }
            else
            {
                if (UID != 0)
                {
                    statement =  "Select * from userAccount where UID =" + UID;
                }
                else
                {
                    statement =  "Select * from userAccount where UID is NOT NULL";
                }

                if (name != null)
                {
                    statement += " and name LIKE '%" + name + "%'";
                }

                if (DOB != null)
                {
                    statement += " and DOB LIKE '%" + DOB + "%'";
                }

                if (user != null)
                {
                    statement += " and user LIKE '%" + user +"%'";
                }

                if (password != null)
                {
                    statement += " and password LIKE '%" + password +"%'";
                }

                if (phoneNo != null)
                {
                    statement += " and phoneNo LIKE '%" + phoneNo+"%'";
                }

                if (email != null)
                {
                    statement += " and email LIKE '%" + email +"%'";
                }

                if (address != null)
                {
                    statement += " and address LIKE '%" + address+ "%'";
                }
                
                if (profileID != 0)
                {
                    statement += " and profileID =" + profileID;
                }
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
                String phoneNo1 = rset.getString("phoneNo");
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
                
                int profileID1 = rset.getInt("profileID");
                userAccount output = new userAccount (UID1, name1, DOB1, user1, password1,
                                                    phoneNo1, email1, address1, suspended2, profileID1);
                searched = addX(searched.length, searched, output);
            }
            return searched;
        }
        catch (SQLException e)
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
   
      public static int[] addX(int n, int arr[], int x)
   {
       int i;
   
       // create a new array of size n+1
       int newarr[] = new int[n + 1];
   
       // insert the elements from
       // the old array into the new array
       // insert all elements till n
       // then insert x at n+1
       for (i = 0; i < n; i++)
           newarr[i] = arr[i];
   
       newarr[n] = x;
   
       return newarr;
   }
   
   public int[] profileIDs () throws ClassNotFoundException
   {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (URL, "admin", "admin");

            PreparedStatement myStmt;
            String statement = "Select * from userprofile";
            int[] searched = new int[0];
            
            myStmt = myCon.prepareStatement(statement);
            
            ResultSet rset = myStmt.executeQuery();
            
            while (rset.next())
            {
                int id = rset.getInt("profileID");
                searched = addX(searched.length, searched, id);
            }
            return searched;
        }
        catch (SQLException e)
        {
            return null;
        }
   }
   
    public boolean validateLogin(String username, String password) throws SQLException, Exception 
    {
       
        String query = "SELECT COUNT(*) FROM useraccount WHERE user = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isValidLogin = false;
        Connection myCon = null;
    
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
       
            myCon = DriverManager.getConnection (URL, "admin", "admin");
            statement = myCon.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
        // Close the resources in the reverse order of their creation
            try 
            {
                if (resultSet != null) 
                {
                    resultSet.close();
                }
                if (statement != null) 
                {
                    statement.close();
                }
                if (myCon != null) 
                {
                    myCon.close();
                }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
        return isValidLogin;
    }
}