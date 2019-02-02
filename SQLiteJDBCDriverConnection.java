import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class SQLiteJDBCDriverConnection {

    public static void main(String[] args) {
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/test.db");
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}