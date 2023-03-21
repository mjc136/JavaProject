package Product;

import java.sql.*;

import javax.swing.JOptionPane;

public class InsertItem{

    public InsertItem(String itemName, double itemPrice, int itemStock){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
     
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Products (item_name, item_price, item_stock) VALUES (?,?,?)");
            pstat.setString (1, itemName);
            pstat.setDouble(2, itemPrice);
            pstat.setInt(3, itemStock);
            // insert data into table
            pstat.executeUpdate();
            // If the insert was successful, display a success message to the user
            JOptionPane.showMessageDialog(null, "Item added successfully!");
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally {
            try {
                pstat.close();
                connection.close();
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class
