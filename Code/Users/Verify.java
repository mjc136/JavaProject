package Users;

import java.sql.*;
import javax.swing.JOptionPane;
import GUI.GUIHandler;

public class Verify {
    
    public Verify(int id, String password){

    }

    public  boolean verifier(int id, String password){
        
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null;
        PreparedStatement pstat= null;
        ResultSet resultSet = null;
        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT password FROM customers WHERE customer_id = ?");
            pstat.setInt(1, id);
            // query data in the table
            resultSet = pstat.executeQuery();
            // process query results
            if(resultSet.next()){
                String userPassword = resultSet.getString("password");
                if(userPassword.equals(password)){
                    return true; // true if password entered matches one in the sql table
                }
                else{
                    JOptionPane.showMessageDialog(GUIHandler.handler, "Incorrect password. Please try again."); // display an error message
                    return false;
                }
            }    
            else{
                JOptionPane.showMessageDialog(GUIHandler.handler, "Id does not exist. Please try again."); // display an error message
                return false;
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
        finally{
            try{
                resultSet.close();
                pstat.close();
                connection.close();
            }
                catch (Exception exception){
                exception.printStackTrace();
            }
        }

    }

}
