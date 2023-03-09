package Customer;

import java.sql.*;

public class UpdateCustomer {
       public UpdateCustomer(int id, String firstname, String lastname, String address, String email, String phoneNum, Date dob){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat = null;
        
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update Customers SET First_name=?, Last_name=?, address=?, email=?, phone_number=?, date_of_birth=? WHERE customer_id=?");
            pstat.setString(1, firstname );
            pstat.setString(2, lastname);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNum);
            pstat.setDate(6, dob);
            pstat.setInt(7, id);
            //Update data in the table
            pstat.executeUpdate();
        }
        catch(SQLException sqlException ) {
            sqlException.printStackTrace () ;
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
    }
} // end class
            
