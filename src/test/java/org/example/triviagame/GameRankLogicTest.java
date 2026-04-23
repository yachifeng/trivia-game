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
 * @since 4/23/26
 */
public class GameRankLogicTest {
    /**
     * Verifies that the rating logic correctly categorizes scores into
     * Excellent, Good, and Keep Practicing based on predefined thresholds,
     * including boundary value analysis.
     */
    @Test
    public void testGetPerformanceRating() {
        // Thresholds: >= 80: Excellent, 50-79: Good, <50: Keep Practicing

        // --- Test Excellent Range (>= 80) ---
        assertEquals("Excellent", getRating(90), "Scores above 80 should be Excellent.");
        assertEquals("Excellent", getRating(80), "Score of exactly 80 should be Excellent.");

        // --- Test Good Range (50-79) ---
        assertEquals("Good", getRating(79), "Score just below 80 should be Good.");
        assertEquals("Good", getRating(70), "Mid-range score should be Good.");
        assertEquals("Good", getRating(50), "Score of exactly 50 should be Good.");

        // --- Test Keep Practicing Range (< 50) ---
        assertEquals("Keep Practicing", getRating(49), "Score just below 50 should be Keep Practicing.");
        assertEquals("Keep Practicing", getRating(30), "Low scores should be Keep Practicing.");
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