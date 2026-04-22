package org.example.triviagame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * Controller class for the Game Over scene.
 * Displays the final score and provides navigation options back to the game or menu.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/22/26
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
    }

    /**
     * Handles the "Main Menu" button click to return to the starting screen.
     * @param event The action event triggered by the button click.
     */
    @FXML
    private void handleReturnToMenu(ActionEvent event) {
        System.out.println("Returning to main menu...");
    }
}