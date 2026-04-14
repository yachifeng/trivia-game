package org.example.triviagame;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Handles SQLite database connections and table creation to ensure data persistence for users, questions, and scores.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/14/26
 */

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:trivia_game.db";

    /**
     * Establishes a connection to the SQLite database using the predefined JDBC URL.
     * This method serves as a central factory for obtaining database connections
     * required for CRUD operations across the application.
     *
     * @return A {@link java.sql.Connection} object representing the active database session.
     * @throws Exception if the JDBC driver is missing or if the database file cannot be accessed.
     */
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }


}
