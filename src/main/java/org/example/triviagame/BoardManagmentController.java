package org.example.triviagame;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

import static org.example.triviagame.SceneSwitcher.switchScene;

/**
 * [Brief Description of this class]
 *
 * @author KMB, Diego Borjas (The leaderboard display is copied from his file)
 * @version 0.1.0
 * @since 4/23/2026
 */
public class BoardManagmentController {

    @FXML
    private void handleDeleteSelected(){
        LeaderboardEntry selected = leaderboardTable.getSelectionModel().getSelectedItem();

        if (selected == null){
            System.out.println("No Entry Selected");
            return;
        }

        int userId = UserDAO.getUserIdByUsername(selected.getUsername());
        ScoreDAO.deleteScoresByEntry(
                userId,
                selected.getScore()
        );

        loadBoardData();
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        switchScene(event, SceneType.ADMINTOOLS);
    }

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


    /**
     * Method to load board data, reusable for the sake of udpating the board after adjusting entries.
     */
    private void loadBoardData(){
        // creates scoresDAO class to call getLeaderboard function for the leaderboard controller to use
        ScoreDAO scores = new ScoreDAO();
        List<LeaderboardEntry> data = scores.getLeaderboard();

        // Debuggin purposes: checks data list size to see if leaderboardDAO method is working
        System.out.println("Leaderboard size: " + data.size());

        // Sets the data from the database as a list onto the leaderboard for viewing
        leaderboardTable.setItems(FXCollections.observableArrayList(data));

    }

    @FXML
    // This will initialize the leaderboard scene and have the display logic.
    public void initialize() {
        System.out.println("Leaderboard scene loaded");

        // Initializes leaderboard allocations: rank, username, and score columns
        rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        //Moved board loading into a method so it could be used beyond initialize.
        loadBoardData();

    }
}
