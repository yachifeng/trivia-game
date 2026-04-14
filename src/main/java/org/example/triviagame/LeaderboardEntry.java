package org.example.triviagame;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class to leaderboard entries that will be used by the leaderboard view class using TableView.
 *
 * @author Diego Borjas
 * @since 4/13/26
 */
public class LeaderboardEntry {
    private final SimpleIntegerProperty rank;
    private final SimpleStringProperty username;
    private final SimpleIntegerProperty score;

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