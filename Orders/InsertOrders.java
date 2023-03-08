import java.sql.*;

class InsertOrders{

    public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        Statement stat = null;
        int i=0;
     
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Statement for inserting data into table
            stat = connection.createStatement();

            // insert data into table

            String sql = "INSERT INTO orders(CustomerID, FirstName, LastName, Address) SELECT CustomerID, FirstName, LastName, Address FROM customers;";
            i = stat.executeUpdate(sql);
            
            sql = "INSERT INTO orders(itemID, itemName, totalPrice) SELECT itemID, itemName, (itemPrice * itemQuantity) FROM products;";
            i = stat.executeUpdate(sql);

            System.out. println ( i + " record successfully added to the table .");
        }
        catch(SQLException sqlException){
            sqlException . printStackTrace () ;
        }
        finally {
            try {
                stat.close () ;
                connection.close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class
