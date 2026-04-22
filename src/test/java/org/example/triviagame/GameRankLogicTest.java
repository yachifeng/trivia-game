package org.example.triviagame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for game performance evaluation logic.
 * This class validates the logic used to categorize player performance
 * based on their final score, independent of the database.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/22/26
 */
public class GameRankLogicTest {
    /**
     * Verifies that the rating logic correctly categorizes scores into
     * Excellent, Good, and Keep Practicing based on predefined thresholds.
     */
    @Test
    public void testGetPerformanceRating() {
        // Logic thresholds: >= 80: Excellent, 50-79: Good, <50: Keep Practicing

        // Test high score
        assertEquals("Excellent", getRating(90), "Scores 80 and above should be Excellent.");

        // Test mid-range score
        assertEquals("Good", getRating(70), "Scores between 50 and 79 should be Good.");

        // Test low score
        assertEquals("Keep Practicing", getRating(30), "Scores below 50 should be Keep Practicing.");
    }

    /**
     * Helper method that defines the performance rating logic.
     * In a production environment, this could be moved to a Utility or Service class.
     *
     * @param score the final quiz score
     * @return a descriptive string representing the performance rank
     */
    private String getRating(int score) {
        if (score >= 80) return "Excellent";
        if (score >= 50) return "Good";
        return "Keep Practicing";
    }
}


