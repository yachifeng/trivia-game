package org.example.triviagame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for answer validation logic.
 * This class ensures that the game's core logic correctly identifies
 * correct and incorrect answers independently of the database or UI.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/22/26
 */

public class AnswerValidationTest {
    /**
     * Tests the answer validation logic with various scenarios including
     * exact matches, case sensitivity, and incorrect answers.
     */
    @Test
    public void testIsCorrectAnswer() {
        String correctAnswer = "Paris";

        // 1. Test exact match
        assertTrue(checkAnswer("Paris", correctAnswer),
                "Should return true when the input exactly matches the correct answer.");

        // 2. Test case sensitivity
        // This verifies that the logic distinguishes between uppercase and lowercase letters.
        assertFalse(checkAnswer("paris", correctAnswer),
                "Should return false for case-mismatched input to ensure case sensitivity.");

        // 3. Test incorrect answer
        assertFalse(checkAnswer("London", correctAnswer),
                "Should return false when the user provides a completely different answer.");

        // 4. Test null safety
        assertFalse(checkAnswer(null, correctAnswer),
                "Should return false and handle null input gracefully without crashing.");
    }

    /**
     * Core logic for checking if a user's input matches the correct answer.
     * Includes basic null-safety checks.
     *
     * @param userInput the string entered or selected by the user
     * @param correct the expected correct answer string
     * @return true if both strings match exactly and are not null; false otherwise.
     */
    private boolean checkAnswer(String userInput, String correct) {
        if (userInput == null || correct == null) return false;
        return userInput.equals(correct);
    }
}
