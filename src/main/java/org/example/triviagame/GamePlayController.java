package org.example.triviagame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * Controller class for the Gameplay Scene, managing the user interface
 * and interaction logic for trivia questions.
 *
 * @author Yachi Feng, Anthony Ou
 * @version 21.0.10
 * @since 4/22/26
 */

public class GamePlayController {
    @FXML private Label questionLabel;
    @FXML private Button btnA, btnB, btnC, btnD;
    @FXML private Label statusLabel;

    private QuestionDAO questionDAO = new QuestionDAO();

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. It sets up the initial question display.
     */
    @FXML
    public void initialize() {
        loadFirstQuestion();
    }

    /**
     * Loads the initial question into the UI components.
     * Currently utilizes placeholder data for initial layout testing.
     */
    private void loadFirstQuestion() {
        // Temporary placeholder for initial UI testing
        questionLabel.setText("What is the primary purpose of a DAO in Java?");
        btnA.setText("Data Access Object");
        btnB.setText("Direct Apple Option");
        btnC.setText("Digital Audio Output");
        btnD.setText("Distributed Array Object");
    }

    /**
     * Processes the user's answer selection and updates the status display.
     * Compares the text of the clicked button against the expected correct answer.
     *
     * @param event The action event triggered by clicking an answer button.
     */
    @FXML
    private void handleAnswer(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String selectedAnswer = clickedButton.getText();

        // Basic logic for answer validation and UI feedback
        if (selectedAnswer.equals("Data Access Object")) {
            statusLabel.setText("Ding Ding Ding! You are correct!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText("Incorrect, Let's try that again. You can do it!!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
