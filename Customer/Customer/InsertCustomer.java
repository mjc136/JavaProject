package Customer;

import java.sql.*;

public class InsertCustomer{

    
    public InsertCustomer(String firstName, String lastName, String address, String email, String phoneNum, Date dob){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        int i=0;
     
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Customers (first_name, last_name, address, email, phone_number) VALUES (?,?,?,?,?)");
            pstat . setString (1, firstName );
            pstat . setString (2, lastName);
            pstat . setString (3, address);
            pstat . setString (4, email);
            pstat . setString (5, phoneNum);
            // insert data into table
            i = pstat .executeUpdate();
            System.out.println ( i + " record successfully added to the table .");
        }
        catch(SQLException sqlException){
            sqlException . printStackTrace () ;
        }
        finally {
            try {
                pstat . close () ;
                connection. close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    }
} // end class
