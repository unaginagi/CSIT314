import java.sql.*;

class userAccount
{
    Int UID;
    String name;
    Date DOB;
    String user;
    String password;
    Int phoneNo;
    String email;
    String address;
    boolean suspended;

    public userAccount ()  
    {

    }

    public userAccount (Int UID, String name, Date DOB, String user, String password, 
                        Int phoneNo, String email, String address, boolean suspended)
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

    public bool addUserAccount (String name, Date DOB, String user, String password,
                               Int phoneNo, String email, String address) throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("INSERT INTO userAccount (name, DOB, user, password," +
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

    public userAccount getUserAccount (Int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("Select * from userAccount where UID = " + UID);
            ResultSet rset = myStmt.executeQuery();
            String name = rset.getString("name");
            Date DOB = rset.getDate("DOB");
            String user = rset.getString("user");
            String password = rset.getString("password");
            Int phoneNo = rset.getInt("phoneNo");
            String email = rset.getString("email");
            String address = rset.getString("address");
            boolean suspended = rs.getBoolean("suspended");

            userAccount output = new userAccount (UID, name, DOB, user, password,
                                                 phoneNo, email, address, suspended);
      
            return output;
        }
        catch (SQLException e )
        {
            return null;
        }
    }

  
    public bool updatingUserAccount (Int UID, String name, Date DOB, String user, String password,
                                    Int phoneNo, String email, String address) 
                                    throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("Update userAccount SET name = " + name + ", DOB = " + DOB
                                              + ", user = " + user + ", password = " + password 
                                              + ", phoneNo = " + phoneNo + ", email = " + email 
                                              + ", address = " + address + "WHERE UID = " + UID);
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public bool updatingUserAccount (Int UID, String name, Date DOB, String user, String password,
                                    Int phoneNo, String email, String address) 
                                    throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("Update userAccount SET name = " + name + ", DOB = " + DOB
                                              + ", user = " + user + ", password = " + password 
                                              + ", phoneNo = " + phoneNo + ", email = " + email 
                                              + ", address = " + address + "WHERE UID = " + UID);
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public bool suspendUserAccount (Int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("Update userAccount SET suspended = " + true 
                                            + "WHERE UID = " + UID);
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public bool unSuspendUserAccount (Int UID) throws SQLException, ClassNotFoundException
    {
        try
        {
            PreparedStatement myStmt; 
            myStmt = con.prepareStatement("Update userAccount SET suspended = " + false 
                                            + "WHERE UID = " + UID);
            return true;
        }
        catch (SQLException e )
        {
            return false;
        }
    }

    public userAccount[] getUserAccount (Int UID, String name, Date DOB, String user, String password,
                                        Int phoneNo, String email, String address)
                                         throws SQLException, ClassNotFoundException
    {
        try
        {
            userAccount [] searched;
            PreparedStatement myStmt;
            myStmt = con.prepareStatement("Select * from userAccount where UID = " + UID + " or name = " +
                                            name + " or DOB = " + DOB + " or user = " + user + " or password = "+
                                            password + " or phoneNo = " + phoneNo + " or email = " + email +
                                            "or address = " + address);
            
            ResultSet rset = myStmt.executeQuery();

            while (rset.next())
            {
                String UID1 = rset.getInt("UID");
                String name1 = rset.getString("name");
                Date DOB1 = rset.getDate("DOB");
                String user1 = rset.getString("user");
                String password1 = rset.getString("password");
                Int phoneNo1 = rset.getInt("phoneNo");
                String email1 = rset.getString("email");
                String address1 = rset.getString("address");
                boolean suspended = rset.getBoolean("suspended");

                userAccount output = new userAccount (UID1, name1, DOB1, user1, password1,
                                                    phoneNo1, email1, address1, suspended);
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



  public static void main (String args []) throws SQLException, ClassNotFoundException
  {
    String URL = "jdbc:mysql://localhost/";
    Class.forName ("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection (URL + "csit314",  "root", "root");
  }
}
