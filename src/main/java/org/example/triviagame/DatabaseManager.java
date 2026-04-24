package org.example.triviagame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

    /**
     * Initializes the database schema by creating the required tables if they do not already exist.
     * This method ensures that the USERS, QUESTIONS, and SCORES tables are set up according to ERD.
     * * It handles the creation of:
     * - USERS: Stores account credentials and roles.
     * - QUESTIONS: Maintains the trivia question bank.
     * - SCORES: Tracks user performance with foreign key constraints.
     */
    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create USERS table (R01 Requirement)
            stmt.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)");

            // Create QUESTIONS table (R01 Requirement)
            stmt.execute("CREATE TABLE IF NOT EXISTS QUESTIONS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "category_id INTEGER NOT NULL, " +
                    "question_text TEXT NOT NULL, " +
                    "option_a TEXT NOT NULL, " +
                    "option_b TEXT NOT NULL, " +
                    "option_c TEXT NOT NULL, " +
                    "option_d TEXT NOT NULL, " +
                    "correct_answer TEXT NOT NULL)");

            // Create SCORES table (R01 Requirement)
            stmt.execute("CREATE TABLE IF NOT EXISTS SCORES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "score INTEGER NOT NULL, " +
                    "date_taken DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(user_id) REFERENCES USERS(id))");

            System.out.println("Database tables initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Temporal method to test database population and data retrieval for the leaderboard. TODO: REMOVE ALL THIS
     */
    public static void seedTestData() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // Clear old data
            stmt.executeUpdate("DELETE FROM SCORES");
            stmt.executeUpdate("DELETE FROM USERS");

            // Reset auto-increment counters
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='USERS'");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='SCORES'");

            // Insert users
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Anthony', 'pass', 'user')");
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Kaleb', 'pass', 'admin')");
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Yachi', 'pass', 'moderator')");

            // Insert scores
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (1, 100)");
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (1, 80)");
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (2, 90)");
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (3, 70)");

            System.out.println("Seed populated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
