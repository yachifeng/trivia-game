package org.example.triviagame;
import java.sql.*;

/**
 * [Manages the data access operations for the QUESTIONS table,
 * enabling CRUD functionality for the trivia question bank.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/14/26
 */
public class QuestionDAO {
    /**
     * 1. Insert
     * Inserts a new trivia question into the database
     * with its category and multiple-choice options.
     *
     * @param categoryId The ID of the category this question belongs to.
     * @param text       The actual text of the trivia question.
     * @param a          The text for option A.
     * @param b          The text for option B.
     * @param c          The text for option C.
     * @param d          The text for option D.
     * @param answer     The string representation of the correct answer (e.g., "A", "B", "C", or "D").
     * @return true if the insertion was successful; false otherwise.
     */
    public boolean insertQuestion(int categoryId, String text, String a, String b, String c, String d, String answer) {
        String sql = "INSERT INTO QUESTIONS(category_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            pstmt.setString(2, text);
            pstmt.setString(3, a);
            pstmt.setString(4, b);
            pstmt.setString(5, c);
            pstmt.setString(6, d);
            pstmt.setString(7, answer);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

}
