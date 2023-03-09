package Customer;

import java.sql.*;

import javax.swing.JOptionPane;

public class InsertCustomer{

    
    public InsertCustomer(String firstName, String lastName, String address, String email, String phoneNum, Date dob){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Customers (first_name, last_name, address, email, phone_number, date_of_birth) VALUES (?,?,?,?,?,?)");
            pstat.setString(1, firstName );
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNum);
            pstat.setDate(6, dob);

            // insert data into table
            pstat.executeUpdate();
            // If the insert was successful, display a success message to the user
            JOptionPane.showMessageDialog(null, "Customer added successfully!");
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                pstat.close() ;
                connection.close () ;
            }
            catch (Exception exception){
                exception.printStackTrace () ;
            }
        }
    }
} // end class
