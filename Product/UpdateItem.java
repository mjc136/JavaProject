import java.sql.*;

public class UpdateItem {
       public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        String itemName = "";
        double itemPrice = 0;
        int itemStock = 0;
        int i=0;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update products SET ItemName=? Where ItemPrice=?");
            pstat . setString (1, itemName );
            pstat . setDouble(2, itemPrice);
            pstat . setInt(3, itemStock);
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
            
