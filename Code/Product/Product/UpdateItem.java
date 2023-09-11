package Product;

import java.sql.*;

public class UpdateItem {
       public UpdateItem(int id,String itemName, double itemPrice, int itemStock){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update products SET item_name=?, item_price=?, item_stock=? WHERE item_id=?");
            pstat . setString (1, itemName );
            pstat . setDouble(2, itemPrice);
            pstat . setInt(3, itemStock);
            pstat . setInt(4, id);
            //Update data in the table
            pstat.executeUpdate();
        }
        catch(SQLException sqlException ) {
            sqlException . printStackTrace () ;
        }
        finally {
            try{
                pstat.close();
                connection.close();
            }
            catch(Exception exception){
                exception.printStackTrace();
            }
        }
    } // end main
} // end class
            
