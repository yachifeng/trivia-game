package org.example.triviagame;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides Data Access Object (DAO) methods for managing quiz score history,
 * including inserting and retrieving user quiz results through the database.
 *
 * @author Anthony Ou
 * @version 1.0
 * @since 4/12/26
 */

public class ScoreDAO {
    /**
     * Inserts a new score record for the user.
     *
     * @param userId the ID of the user
     * @param score the quiz score to be stored
     */
    public void insertScore(int userId, int score) {
        String sql = "INSERT INTO SCORES(user_id, score) VALUES(?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all quiz score records for a specific user,
     * ordered by most recent attempts.
     *
     * @param userId the ID of the user
     * @return a list of ScoreRecord objects containing the user's quiz history
     */
    public List<ScoreRecord> getUserScores(int userId) {
        List<ScoreRecord> scores = new ArrayList<>();
        String sql = "SELECT score, date_taken FROM SCORES WHERE user_id = ? ORDER BY date_taken DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int score = rs.getInt("score");
                String dateTaken = rs.getString("date_taken");
                scores.add(new ScoreRecord(score, dateTaken));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scores;
    }

    /**
     * Deletes all score records associated with a specific user.
     * This method is primarily used for testing purposes.
     *
     * @param userId the ID of the user
     */
    public void deleteScoresByUser(int userId) {
        String sql = "DELETE FROM SCORES WHERE user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add-on method to view leaderboard and connect user scores with leaderboard display. Since scoreDAO class handles
     * scores issues such as inserts and reading, it should also send data back to the leaderboard for display from
     * our database.
     *
     * @return leaderboard as a list
     */
    public List<LeaderboardEntry> getLeaderboard() {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        String sql = """
        SELECT U.username, MAX(S.score) AS best_score
        FROM SCORES S
        JOIN USERS U ON S.user_id = U.id
        GROUP BY U.id, U.username
        ORDER BY best_score DESC
    """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            int rank = 1;
            while (rs.next()) {
                leaderboard.add(new LeaderboardEntry(
                        rank++,
                        rs.getString("username"),
                        rs.getInt("best_score")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return leaderboard;
    }
}
