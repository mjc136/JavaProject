package Orders;

import java.sql.*;

public class UpdateOrders{
       public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i=0;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update Customers SET LastName=? Where FirstName=?");

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
            
