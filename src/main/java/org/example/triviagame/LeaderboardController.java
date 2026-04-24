package org.example.triviagame;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import static org.example.triviagame.SceneSwitcher.switchScene;

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
    private TableColumn<LeaderboardEntry, Number> rankColumn;

    @FXML
    // This will store the leaderboard's second column which contains the usernames.
    private TableColumn<LeaderboardEntry, String> usernameColumn;

    @FXML
    // This will store the leaderboard's third column which will be the user's scores.
    private TableColumn<LeaderboardEntry, Number> scoreColumn;

    @FXML
    // This will initialize the leaderboard scene and have the display logic.
    public void initialize() {
        System.out.println("Leaderboard scene loaded");

        // Initializes leaderboard allocations: rank, username, and score columns
        rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        // creates scoresDAO class to call getLeaderboard function for the leaderboard controller to use
        ScoreDAO scores = new ScoreDAO();
        List<LeaderboardEntry> data = scores.getLeaderboard();

        // Debuggin purposes: checks data list size to see if leaderboardDAO method is working
        System.out.println("Leaderboard size: " + data.size());

        // Sets the data from the database as a list onto the leaderboard for viewing
        leaderboardTable.setItems(FXCollections.observableArrayList(data));

    }

    @FXML
    // Back button which will allow the user to return to the previous scene and exit the leaderboard.
    private void handleBack(ActionEvent event) {
        if(Session.getRole().equals("admin")){
            switchScene(event, SceneType.ADMINTOOLS);
        } else {
            switchScene(event, SceneType.TITLE);
        }
    }
}