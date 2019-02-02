/*
* Summary: how to create a new table from the Java program using SQLite JDBC Driver.
*
* To create a new table in a specific database, use the following steps:
*
* First, prepare the CREATE TABLE statement.
* Second, connect to the database that contains the table.
* Third, create a new instance of the Statement class from the Connection object.
* Fourth, execute the CREATE TABLE statement by calling the executeUpdate() method of the Statement object.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBCDriverNewTable {

    public static void main(String[] args) {
        
        String url = "jdbc:sqlite:C://sqlite/db/test2.db";
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url); // Creates new DB if doesn't exist
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // Creates a new table
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}