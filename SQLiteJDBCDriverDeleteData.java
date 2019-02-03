/* Summary: how to delete data in a table from a Java program using JDBC.
*
* To delete one or more rows of a database table from a Java program, use the following steps:
*
* First, create a database connection to the SQLite database.
* Next, prepare the DELETE statement. In case you want the statement takes parameters, you use the question mark (?) placeholder inside the statement.
* Then, create a new instance of the  PreparedStatement class by calling the prepareStatement() method of the Connection object.
* After that, supply values in place of the question mark placeholder using the set* method of the PreparedStatement object e.g., setInt(), setString(), etc.
* Finally, execute the DELETE statement by calling the executeUpdate() method of the PreparedStatement object.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverDeleteData {
 
    /**
     * Connect to the test.db database
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
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    public void delete(int id) {
        String sql = "DELETE FROM warehouses WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // Set the corresponding param
            pstmt.setInt(1, id);
            // Execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SQLiteJDBCDriverDeleteData app = new SQLiteJDBCDriverDeleteData();
        // Delete the row with id 3
        app.delete(3);
    }
 
}