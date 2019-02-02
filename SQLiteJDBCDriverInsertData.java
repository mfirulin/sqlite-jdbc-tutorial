/* 
* Summary: how to insert data into a table using the JDBC.
*
* To insert data into a table using the INSERT statement, use the following steps:
*
* First, connect to the SQLite database.
* Next, prepare the INSERT statement. If you use parameters for the statement, use a question mark (?) for each parameter.
* Then, create an instance of the PreparedStatement from the Connection object.
* After that, set the corresponding values for each placeholder using the set method of the PreparedStatement object such as setInt(), setString(), etc.
* Finally, call the executeUpdate() method of the PreparedStatement object to execute the statement.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class SQLiteJDBCDriverInsertData {
 
    /**
     * Connect to the database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
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
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name, double capacity) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        SQLiteJDBCDriverInsertData app = new SQLiteJDBCDriverInsertData();
        app.insert("Raw Materials", 3000);
        app.insert("Semifinished Goods", 4000);
        app.insert("Finished Goods", 5000);
    }
 
}