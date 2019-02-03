/*
* Summary: how to query data from a table using Java JDBC.
*
* To query data from a table, use the following steps:
*
* First, create a Connection object to connect to the SQLite database.
* Next, create an instance of the Statement class from the Connection object.
* Then, create an instance of the ResultSet class by calling the executeQuery method of the Statement object. The executeQuery() method accepts a SELECT statement.
* After that, loop through the result set using the next() method of the ResultSet object.
* Finally, use the get* method of the ResultSet object such as getInt(), getString(), getDouble(), etc., to get the data in each iteration.
*/
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SQLiteJDBCDriverSelectData {
 
    /**
     * Connect to the database
     * @return the Connection object
     */
    private Connection connect() {
        String url = "jdbc:sqlite:C://sqlite/db/test2.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    /**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT id, name, capacity FROM warehouses";
        
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            // Loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the warehouse whose capacity greater than a specified capacity
     * @param capacity 
     */
    public void getCapacityGreaterThan(double capacity){
        String sql = "SELECT id, name, capacity FROM warehouses WHERE capacity > ?";
        
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // Set the value
            pstmt.setDouble(1, capacity);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // Loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SQLiteJDBCDriverSelectData app = new SQLiteJDBCDriverSelectData();
        app.selectAll();
        app.getCapacityGreaterThan(3600);
    }
}