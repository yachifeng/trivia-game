package org.example.triviagame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

/**
 * Controller class for the Game Over scene.
 * Displays the final score and provides navigation options back to the game or menu.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/23/26
 */
public class GameOverController {

    @FXML private Label scoreLabel;

    /**
     * Sets the final score to be displayed on the screen.
     * This method can be called by the GamePlayController before transitioning.
     *
     * @param score The total points earned by the player.
     */
    public void setFinalScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    /**
     * Handles the "Play Again" button click to restart the trivia game.
     * * @param event The action event triggered by the button click.
     */
    @FXML
    private void handleRestart(ActionEvent event) {
        System.out.println("Restarting game...");
        navigateToScene(event, "GamePlayScene.fxml");
    }

    /**
     * Handles the "Main Menu" button click to return to the starting screen.
     * @param event The action event triggered by the button click.
     */
    @FXML
    private void handleReturnToMenu(ActionEvent event) {
        System.out.println("Returning to main menu...");
        // Switching to TitleScene based on your resource file list
        navigateToScene(event, "TitleScene.fxml");
    }

    /**
     * Helper method to handle scene transitions.
     * @param event The action event to identify the current stage.
     * @param fxmlFile The FXML file to load.
     */
    private void navigateToScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error: Could not load scene " + fxmlFile);
            e.printStackTrace();
        }
    }
}
