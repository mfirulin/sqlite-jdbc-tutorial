import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
 
public class SQLiteJDBCDriverConnection {

    public static void main(String[] args) {
        
        Connection conn = null;
        DatabaseMetaData meta = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/test.db");
            meta = conn.getMetaData();
            System.out.println("Connection to SQLite has been established. The driver name is " + meta.getDriverName());
            
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