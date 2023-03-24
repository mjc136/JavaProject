package Orders;

import java.sql.*;

import javax.swing.JOptionPane;

import Users.UserSession;

public class InsertOrders{

    public InsertOrders(int orderAmount, int itemId){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        double price = 0;
        double totalPrice = 0;
        Date date;
        int orderId = 0;
        
        try {
            
            
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            String sql = "INSERT INTO orders(customer_id, order_quantity, item_id) VALUES (?, ?, ?)";
            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, UserSession.getUser()); // stores current user as customer id 
            pstat.setInt(2, orderAmount);
            pstat.setInt(3, itemId);
            pstat.executeUpdate();
            pstat.close();
            
            // get item price
            sql = "SELECT item_price FROM Products WHERE item_id=?";
            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, itemId);
            ResultSet rs = pstat.executeQuery();
            if(rs.next()){
                price = rs.getInt("item_price");
            }
            pstat.close();
            rs.close();

            // get the order_id of the last inserted row
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
            orderId = rs.getInt(1);
            // use the orderId as needed
            }
            rs.close();
            stmt.close();

            // update customer details for the current order
            sql = "UPDATE orders o " +
            "INNER JOIN customers c ON o.customer_id = c.customer_id " +
            "SET o.first_name = c.first_name, " +
            "o.last_name = c.last_name, " +
            "o.address = c.address " +
            "WHERE o.order_id = ?";
            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, orderId); // use the order ID from the insert statement
            pstat.executeUpdate();

            // update item details for the current order
            sql = "UPDATE orders o " +
            "INNER JOIN products p ON o.item_id = p.item_id " +
            "SET o.item_name = p.item_name " +
            "WHERE o.order_id = ?";
            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, orderId); // use the order ID from the insert statement
            pstat.executeUpdate();

            // get the total price of the item
            sql = "SELECT item_price FROM products WHERE item_id=?";
            pstat = connection.prepareStatement(sql);
            pstat.setInt(1, itemId); // set the item ID
            rs = pstat.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("item_price");
            }
            rs.close();
            pstat.close();

            totalPrice = price * orderAmount;

            // update total price for the current order
            sql = "UPDATE orders SET total_price = ? WHERE order_id = ?";
            pstat = connection.prepareStatement(sql);
            pstat.setDouble(1, totalPrice);
            pstat.setInt(2, orderId); // use the order ID from the first insert statement
            pstat.executeUpdate();

            // date of purchase
            date = new java.sql.Date(System.currentTimeMillis());

            // set date of order
            sql = "UPDATE orders SET date_of_purchase = ? WHERE order_id = ?";
            pstat = connection.prepareStatement(sql);
            pstat.setDate(1, date);
            pstat.setInt(2, orderId); // use the order ID from the first insert statement
            // insert data into table
            pstat.executeUpdate();
            // If the insert was successful, display a success message to the user
            JOptionPane.showMessageDialog(null, "Order added successfully!");
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
                exception.printStackTrace();
            }
        }
    } // end main
} // end class