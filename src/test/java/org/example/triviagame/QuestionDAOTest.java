package org.example.triviagame;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
     * This method verifies three key database operations:
     * 1. Insert: Adding a new question.
     * 2. Update: Modifying existing question text.
     * 3. Delete: Removing the question record.
     */
    @Test
    public void testQuestionCRUD() {
        // 1. Test Insert - Directly retrieves the generated primary key
        int id = questionDAO.insertQuestion(1, "What is Java?", "Fruit", "Car", "Language", "City", "C");

        // Verify that the ID is greater than 0 (SQLite AUTOINCREMENT typically starts at 1)
        assertTrue(id > 0, "Question should be inserted and return a valid ID");
        System.out.println("Generated ID: " + id);

        // 2. Test Update - Uses the correctly retrieved ID from the insertion step
        boolean updated = questionDAO.updateQuestionText(id, "Updated Question Content?");
        assertTrue(updated, "Question text should be updated successfully");

        // 3. Test Delete - Verifies that the record can be removed using its ID
        boolean deleted = questionDAO.deleteQuestion(id);
        assertTrue(deleted, "Question should be deleted successfully");
    }

    /**
     * Tests the Read functionality of the database by inserting a record
     * and verifying it can be retrieved in a list.
     * This satisfies the "Read" requirement of a functional database.
     */
    @Test
    public void testReadQuestions() {
        // Insert a temporary question to ensure the list is not empty
        questionDAO.insertQuestion(1, "Read Test Question", "A", "B", "C", "D", "A");

        // Execute Read operation
        List<String> questions = questionDAO.getAllQuestionTexts();

        // Verify results
        assertNotNull(questions, "The returned list should not be null");
        assertFalse(questions.isEmpty(), "The question list should contain at least one item");
        assertTrue(questions.contains("Read Test Question"), "The list should contain the inserted test question");
    }
}