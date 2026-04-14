package org.example.triviagame;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @Author Diego Borjas
 * @Since 4/14/2026
 *
 * Leaderboard Controller class which will handle the logic of the leaderboard UI logic using TableView.
 */
public class LeaderboardController {

    @FXML
    private TableView<LeaderboardEntry> leaderboardTable;

    @FXML
    private TableColumn<LeaderboardEntry, Integer> rankColumn;

    @FXML
    private TableColumn<LeaderboardEntry, String> usernameColumn;

    @FXML
    private TableColumn<LeaderboardEntry, Integer> scoreColumn;

    @FXML
    public void initialize() {
        System.out.println("Leaderboard scene loaded");
    }

    @FXML
    private void handleBack() {
        System.out.println("Back button clicked");
    }
}