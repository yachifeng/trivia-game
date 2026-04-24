package org.example.triviagame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Anthony Ou, Yachi Feng
 * @version 0.2.0
 * @since 4/22/26
 *
 * Unit test for the ScoreDAO class.
 */


public class ScoreDAOTest{

    private ScoreDAO scoreDAO = new ScoreDAO();


    /**
     * Initializes database before any tests are executed
     * to ensure required tables exist for testing.
     */
    @BeforeAll
    public static void setup() {
        DatabaseManager.initializeDatabase();
    }


    /**
     * Cleans up test data after each test case.
     * Maintains the testing environment.
     */
    @AfterEach
    public void tearDown() {
        scoreDAO. deleteScoresByUser(1);
    }


    /**
     * To test insert and retrieval functions of ScoreDAO.
     * Verifies the scores inserted into the database so
     * it can successfully retrieve and match expected values.
     */
    @Test
    public void testInsertAndRetrieveScores() {

        // Insert test scores
        scoreDAO.insertScore(1, 70);
        scoreDAO.insertScore(1, 80);

        // To retrieve scores
        List<ScoreRecord> scores = scoreDAO.getUserScores(1);

        // to verify results
        assertNotNull(scores, "List of scores should not be null.");
        assertTrue(scores.size() >= 2, "Retrieve at least 2 scores.");

        assertTrue(scores.stream().anyMatch(score -> score.getScore() == 70),
                "Score list should contain 70.");

        assertTrue(scores.stream().anyMatch(score -> score.getScore() == 80),
                "Score list should contain 80.");

    }


    /**
     * Tests the update function of ScoreDAO.
     * Verifies that an existing score can be modified and correctly retrieved.
     */
    @Test
    public void testUpdateScore() {
        int testUserId = 1;
        scoreDAO.insertScore(testUserId, 70);

        // Update the score we just inserted
        scoreDAO.updateScore(testUserId, 95);

        List<ScoreRecord> scores = scoreDAO.getUserScores(testUserId);
        assertFalse(scores.isEmpty(), "Score list should not be empty.");
        assertEquals(95, scores.get(0).getScore(), "The most recent score should be updated to 95.");
    }


    /**
     * Tests the delete function of ScoreDAO.
     * Verifies that all score records for a user are removed correctly.
     */
    @Test
    public void testDeleteScores() {
        int testUserId = 1;
        scoreDAO.insertScore(testUserId, 88);

        // Action: Delete the scores
        scoreDAO.deleteScoresByUser(testUserId);

        // Verification: The list should be empty
        List<ScoreRecord> scores = scoreDAO.getUserScores(testUserId);
        assertTrue(scores.isEmpty(), "Score list should be empty after deletion.");
    }


    /**
     * Test behavior of ScoreDAO on retrieving scores for new users.
     * This will verify the user with no existing score records return as empty list.
     */
    @Test
    public void testNewUserNoScores() {
        int testUserId = 999;

        List<ScoreRecord> scores = scoreDAO.getUserScores(testUserId);

        assertTrue(scores.isEmpty(), "Scores should be left empty for new users.");

    }
}