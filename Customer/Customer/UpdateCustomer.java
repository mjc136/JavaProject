package Customer;

import java.sql.*;

public class UpdateCustomer {
       public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        String firstname="Lisa";
        String lastname="Brennan";
        String address  = "1 river street";
        String email = "john@gmail.com";
        String phoneNum = "0876453643";
        int i=0;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update Customers SET LastName=? Where FirstName=?");
            pstat . setString (1, firstname );
            pstat . setString (2, lastname);
            pstat . setString (3, address);
            pstat . setString (4, email);
            pstat . setString (5, phoneNum);
            //Update data in the table
            i = pstat.executeUpdate();
            System.out. println ( i + " record successfully updated in the table .");
        }
        catch(SQLException sqlException ) {
            sqlException . printStackTrace () ;
        }
        finally {
            try{
                pstat . close () ;
                connection. close () ;
            }
            catch ( Exception exception ){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class
            
