package Orders;

import java.sql.*;

class InsertOrders{

    public static void main(String[] args) {
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        int orderAmount = 5;
        int i=0;
     
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            String sql1 = "INSERT INTO orders(order_quantity) VALUES (?)";
            pstat = connection.prepareStatement(sql1);
            pstat.setInt(1, orderAmount);
            // insert data into table
            i = pstat.executeUpdate();

            // second statment
            String sql2 = "INSERT INTO orders(customer_id, first_name, last_name, address) SELECT customer_iD, first_name, last_name, address FROM customers;";
            i = pstat.executeUpdate(sql2);
            // third statement
            String sql3 = "INSERT INTO orders(item_iD, item_name) SELECT item_id, item_name FROM products;";
            i = pstat.executeUpdate(sql3);
            // fourth statement
            String sql4 = "UPDATE orders SET total_price = (SELECT order_quantity * item_price FROM products WHERE orders.item_id = products.item_id)";
            i = pstat.executeUpdate(sql4);

            System.out. println ( i + " record successfully added to the table .");
        }
        catch(SQLException sqlException){
            sqlException . printStackTrace () ;
        }
        finally {
            try {
                pstat.close () ;
                connection.close () ;
            }
            catch (Exception exception){
                exception . printStackTrace () ;
            }
        }
    } // end main
} // end class
