package Product;

import java.sql.*;

import javax.swing.JOptionPane;

public class DeleteItem {
    
    public DeleteItem(int itemID){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for deleting data from the table
            pstat = connection.prepareStatement("Delete FROM products WHERE item_id=?");
            pstat.setInt (1, itemID);
            // delete data from the table
            pstat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Deleted!");
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally {
            try{
                pstat.close();
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
} // end class
            
