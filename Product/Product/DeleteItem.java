package Product;

import java.sql.*;

public class DeleteItem {
    
    public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i=0;
        int itemID=1;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for deleting data from the table
            pstat = connection.prepareStatement("Delete From Products Where item_id=?");
            pstat . setInt (1, itemID);
            // delete data from the table
            i = pstat.executeUpdate();
            System.out. println ( i + " record successfully removed from the table .");
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
            
