package org.example.triviagame;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class model that bridges between the database to leaderboard entries which will be used by the leaderboard
 * using TableView.
 *
 * @author Diego Borjas
 * @since 4/13/26
 */
public class LeaderboardEntry {

    // Data will be SimpleSring or SimpleInteger which are acceptable varaibles fofr TableView.
    private final SimpleIntegerProperty rank;
    private final SimpleStringProperty username;
    private final SimpleIntegerProperty score;

    // Constructor that represents a single entry within the leaderboard.
    public LeaderboardEntry(int rank, String username, int score) {
        this.rank = new SimpleIntegerProperty(rank);
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
    }

    public int getRank() {
        return rank.get();
    }

    public String getUsername() {
        return username.get();
    }

    public int getScore() {
        return score.get();
    }
}