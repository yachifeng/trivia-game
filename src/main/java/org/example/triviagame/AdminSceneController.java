package org.example.triviagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 * This is a controller for the Admin Scene to navigate through the tools.
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/21/2026
 */
public class AdminSceneController {
    @FXML
    private void switchScene(ActionEvent event, SceneType type) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneFactory.create(type));
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
}
