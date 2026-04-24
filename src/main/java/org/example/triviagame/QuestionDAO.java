package org.example.triviagame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * 1. Create (Insert)
     * Inserts a new trivia question and returns its generated primary key.
     * * @return The newly generated ID, or -1 if the insertion failed.
     */
    public int insertQuestion(int categoryId, String text, String a, String b, String c, String d, String answer) {
        String sql = "INSERT INTO QUESTIONS(category_id, question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES(?,?,?,?,?,?,?)";

        // Using Statement.RETURN_GENERATED_KEYS to retrieve the auto-incremented ID
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, categoryId);
            pstmt.setString(2, text);
            pstmt.setString(3, a);
            pstmt.setString(4, b);
            pstmt.setString(5, c);
            pstmt.setString(6, d);
            pstmt.setString(7, answer);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Success: returns the actual database ID
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Returns -1 upon failure
    }

    /**
     * 2. Read
     * Reads all question texts from the database.
     * @return A list of question strings.
     */
    public List<String> getAllQuestionTexts() {
        List<String> questions = new ArrayList<>();
        String sql = "SELECT question_text FROM QUESTIONS";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                questions.add(rs.getString("question_text"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return questions;
    }

    /**
     * 3. Update
     * Updates the text of an existing question identified by its unique ID.
     *
     * @param id      The unique ID of the question to be updated.
     * @param newText The new text content for the question.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateQuestionText(int id, String newText) {
        String sql = "UPDATE QUESTIONS SET question_text = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newText);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    /**
     * 4. Delete
     * Permanently removes a question from the database based on its unique ID.
     *
     * @param id The unique ID of the question to be deleted.
     * @return true if the deletion was successful; false otherwise.
     */
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM QUESTIONS WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    /**KBM
     * 5. deleteByText
     * Method to permanently removed a question from the database, using its question text field. For AdminTools
     * In "real world" it would make more sense to use the delete by Id method above.
     * Currently the question class has no way to retrieve that ID (or I looked over it)
     * Writing this method required less refactoring than trying to make the above work in admintools.
     * @param text the text of the question, there shouldn't be duplicate questions so this should be fine.
     */
    public void deleteQuestionByText(String text){
        String sql = "DELETE FROM QUESTIONS WHERE question_text = ?";

        try(Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, text);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the ID of the last row inserted into the database session.
     * This helper method is primarily used for unit testing purposes.
     *
     * @return The ID of the last inserted row, or -1 if an error occurs.
     */
    public int getLastInsertedId() {
        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
            return rs.next() ? rs.getInt(1) : -1;
        } catch (Exception e) { return -1; }
    }

    /**
     * Retrieves all trivia questions from the database, wrapping each record
     * into a {@link Question} model object.
     * This method fulfills the "Read" requirement by fetching the question text,
     * all four multiple-choice options, and the correct answer for every record
     * in the QUESTIONS table.
     *
     * @return A {@link List} of {@link Question} objects containing full question data.
     */
    public List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM QUESTIONS";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Wrap each database row into a Question object
                Question q = new Question(
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                );
                list.add(q);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
