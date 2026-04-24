package org.example.triviagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static org.example.triviagame.SceneSwitcher.switchScene;

/**
 * This is a controller for the title screen, both admins and user functions are in here. But only some are accessible by a standard user.
 *
 * @author KMB
 * @version 0.2.0
 * @since 4/21/2026
 */
public class TitleSceneController {

    @FXML
    private Label usernameLabel;

    @FXML //JavaFX calls initialize when an FXML scene is loaded, this allows us to initialize UI elements that might change, like a username display.
    public void initialize() {
        usernameLabel.setText(Session.getUser());
    }

    @FXML
    private void handleGoToQuestionTools(ActionEvent event) {
        switchScene(event, SceneType.QUESTIONTOOLS);
    }

    @FXML
    private void handleGoToUserTools(ActionEvent event) {
        switchScene(event, SceneType.USERMANAGETOOLS);
    }

    @FXML
    private void handleBoardTools(ActionEvent event) {
        switchScene(event, SceneType.BOARDMANAGETOOLS);
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        switchScene(event, SceneType.ADMINTOOLS);
    } //Currently being used for all tool scenes.

    @FXML
    private void handlePlayGame(ActionEvent event){
        switchScene(event, SceneType.GAMEPLAY);
    }

    @FXML
    private void handleGoToBoard(ActionEvent event){
        switchScene(event, SceneType.LEADERBOARD);
    }
}
