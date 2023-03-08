import java.sql.*;

class InsertCustomer{

    public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        String firstname = "Carolina";
        String lastname = "Martins";
        String address  = "10 chapel lane";
        String email = "carmart@hotmail.co.uk";
        String phoneNum = "0857652367";
        int i=0;
     
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Customers (FirstName, LastName, Address, Email, PhoneNumber) VALUES (?,?,?,?,?)");
            pstat . setString (1, firstname );
            pstat . setString (2, lastname);
            pstat . setString (3, address);
            pstat . setString (4, email);
            pstat . setString (5, phoneNum);
            // insert data into table
            i = pstat .executeUpdate();
            System.out. println ( i + " record successfully added to the table .");
        }
        catch(SQLException sqlException){
            sqlException . printStackTrace () ;
        }
        finally {
            try {
                pstat . close () ;
                connection. close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class
