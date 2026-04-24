package org.example.triviagame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Handles SQLite database connections and table creation to ensure data persistence for users, questions, and scores.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/23/26
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
     * Populates the database with initial seed data for testing purposes.
     * Includes Users, Scores, and Questions.
     */
    public static void seedTestData() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // Clear existing records to avoid duplicate key errors during testing
            stmt.executeUpdate("DELETE FROM SCORES");
            stmt.executeUpdate("DELETE FROM QUESTIONS");
            stmt.executeUpdate("DELETE FROM USERS");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='USERS'");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='QUESTIONS'");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='SCORES'");

            // Seed Users
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Anthony', 'pass', 'user')");
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Kaleb', 'pass', 'admin')");
            stmt.executeUpdate("INSERT INTO USERS(username, password, role) VALUES ('Yachi', 'pass', 'moderator')");

            // Seed Questions (Crucial for UI connection)
            String insertQuestions = "INSERT INTO QUESTIONS(category_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES " +
                    "(1, 'What is the primary purpose of a DAO in Java?', 'Data Access Object', 'Direct Apple Option', 'Digital Audio Output', 'Distributed Array Object', 'Data Access Object'), " +
                    "(1, 'Which language is used for JavaFX layouts?', 'Python', 'FXML', 'C++', 'Swift', 'FXML'), " +
                    "(1, 'What does SQL stand for?', 'Strong Query Language', 'Structured Query Language', 'Simple Queue List', 'System Query Logic', 'Structured Query Language'), " +
                    "(1, 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'includes', 'extends'), " +
                    "(1, 'What is the default value of an int variable in Java?', 'null', '0', '1', '-1', '0'), " +
                    "(1, 'Which SQL command is used to remove all records from a table?', 'DELETE', 'REMOVE', 'DROP', 'TRUNCATE', 'TRUNCATE'), " +
                    "(1, 'In JavaFX, which component is the root container for a scene?', 'Stage', 'Scene', 'Pane', 'Root', 'Pane'), " +
                    "(1, 'What does JVM stand for?', 'Java Virtual Machine', 'Java Visual Manager', 'Java Variable Method', 'Java Version Model', 'Java Virtual Machine'), " +
                    "(1, 'Which SQL keyword is used to sort the result-set?', 'SORT BY', 'ORDER BY', 'GROUP BY', 'ALIGN BY', 'ORDER BY'), " +
                    "(1, 'Which of these is NOT a primitive type in Java?', 'int', 'boolean', 'String', 'char', 'String'), " +
                    "(1, 'What is the primary key in a database?', 'A unique identifier', 'A secondary field', 'A type of index', 'A foreign reference', 'A unique identifier')";

            stmt.executeUpdate(insertQuestions);

            // Seed initial Scores for the leaderboard
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (1, 100)");
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (2, 85)");
            stmt.executeUpdate("INSERT INTO SCORES(user_id, score) VALUES (3, 95)");

            System.out.println("Enhanced seed data populated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

