package Orders;

import java.sql.*;

public class DeleteOrders{
    
    public DeleteOrders(int orderID){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(
            DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for deleting data from the table
            pstat = connection.prepareStatement("Delete From orders Where order_id=?");
            pstat . setInt (1, orderID);
            // delete data from the table
            pstat.executeUpdate();
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
    } // end main
} // end class
            
