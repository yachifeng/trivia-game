package org.example.triviagame;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides unit tests for the QuestionDAO class to verify database CRUD
 * operations for trivia questions, satisfying the O02 project requirement.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/21/26
 */

public class QuestionDAOTest {
    private QuestionDAO questionDAO = new QuestionDAO();

    /**
     * Set up the test environment by initializing the database schema
     * before any tests are executed.
     */
    @BeforeAll
    public static void setup() {
        DatabaseManager.initializeDatabase();
    }

    /**
     * Tests the complete lifecycle of a question record, including insertion,
     * text updating, and deletion to ensure data integrity.
     * * This method verifies three key database operations:
     * 1. Insert: Adding a new question.
     * 2. Update: Modifying existing question text.
     * 3. Delete: Removing the question record.
     */
    @Test
    public void testQuestionCRUD() {
        // 1. Test Insert (Requirement: Database Tests - Insert)
        boolean inserted = questionDAO.insertQuestion(1, "What is Java?", "Fruit", "Car", "Language", "City", "C");
        assertTrue(inserted, "Question should be inserted successfully");

        // Retrieve the generated ID for subsequent update and delete operations
        int id = questionDAO.getLastInsertedId();
        assertTrue(id != -1, "Should be able to retrieve a valid last inserted ID");

        // 2. Test Update (Requirement: Database Tests - Update)
        boolean updated = questionDAO.updateQuestionText(id, "Updated Question Content?");
        assertTrue(updated, "Question text should be updated successfully");

        // 3. Test Delete (Requirement: Database Tests - Delete)
        boolean deleted = questionDAO.deleteQuestion(id);
        assertTrue(deleted, "Question should be deleted successfully");
    }
}