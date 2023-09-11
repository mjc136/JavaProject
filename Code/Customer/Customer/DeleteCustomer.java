package Customer;

import java.sql.*;

import javax.swing.JOptionPane;

public class DeleteCustomer {
    
    public DeleteCustomer(int CustomerID){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for deleting data from the table
            pstat = connection.prepareStatement("Delete From Customers Where customer_id=?");
            pstat . setInt (1, CustomerID);
            // delete data from the table
            pstat.executeUpdate();
            System.out.println();
            JOptionPane.showMessageDialog(null, "Your profile has been deleted!");
        }
        catch(SQLException sqlException ) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        finally {
            try{
                pstat.close();
                connection.close();
            }
            catch( Exception exception ){
                exception.printStackTrace () ;
            }
        }
    }
} // end class
            
