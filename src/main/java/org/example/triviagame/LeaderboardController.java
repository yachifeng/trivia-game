package org.example.triviagame;

import javafx.collections.FXCollections;
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
    // This will store the leaderboard table in its entirety.
    private TableView<LeaderboardEntry> leaderboardTable;

    @FXML
    // This will store the leaderboard's first column which will be the ranks in ordinal scale.
    private TableColumn<LeaderboardEntry, Integer> rankColumn;

    @FXML
    // This will store the leaderboard's second column which contains the usernames.
    private TableColumn<LeaderboardEntry, String> usernameColumn;

    @FXML
    // This will store the leaderboard's third column which will be the user's scores.
    private TableColumn<LeaderboardEntry, Integer> scoreColumn;

    @FXML
    // This will initialize the leaderboard scene and have the display logic.
    public void initialize() {
        System.out.println("Leaderboard scene loaded");
        rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        ScoreDAO scoreDAO = new ScoreDAO();
        leaderboardTable.setItems(FXCollections.observableArrayList(scoreDAO.getLeaderboard()));

    }

    @FXML
    // Back button which will allow the user to return to the previous scene and exit the leaderboard.
    private void handleBack() {
        System.out.println("Back button clicked");
    }
}