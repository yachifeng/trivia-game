package org.example.triviagame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Gameplay Scene, managing the user interface
 * and interaction logic for trivia questions.
 *
 * @author Yachi Feng, Anthony Ou
 * @version 21.0.10
 * @since 4/23/26
 */

public class GamePlayController {
    @FXML private Label questionLabel;
    @FXML private Button btnA, btnB, btnC, btnD;
    @FXML private Label statusLabel;

    private QuestionDAO questionDAO = new QuestionDAO();
    private List<Question> allQuestions;
    private Question currentQuestion;
    private int currentIndex = 0;
    private int score = 0;

    /**
     * Initializes the controller and triggers the database fetch for the first question.
     */
    @FXML
    public void initialize() {
        loadDataFromDatabase();
    }

    /**
     * Fetches all trivia questions and displays the first one.
     */
    private void loadDataFromDatabase() {
        allQuestions = questionDAO.getAllQuestions();
        if (allQuestions != null && !allQuestions.isEmpty()) {
            displayQuestion(currentIndex);
        } else {
            questionLabel.setText("Warning: No questions found in the database.");
        }
    }

    /**
     * Updates the UI with the question at the specified index.
     * Transitions to the completion screen if no more questions remain.
     *
     * @param index The current index in the question list.
     */
    private void displayQuestion(int index) {
        if (allQuestions != null && index < allQuestions.size()) {
            currentQuestion = allQuestions.get(index);

            questionLabel.setText(currentQuestion.getText());
            String[] opts = currentQuestion.getOptions();

            // Set options and ensure buttons are visible
            btnA.setText(opts[0]);
            btnB.setText(opts[1]);
            btnC.setText(opts[2]);
            btnD.setText(opts[3]);

            btnB.setVisible(true);
            btnC.setVisible(true);
            btnD.setVisible(true);

            statusLabel.setText("");
        } else {
            navigateToGameOver();
        }
    }

    /**
     * Transitions the UI to the GameOverScene.
     */
    private void navigateToGameOver() {
        try {
            String currentUsername = Session.getUser(); // get current user
            if (currentUsername != null) {
                UserDAO userDAO = new UserDAO();
                int userId = userDAO.getUserIdByUsername(currentUsername);

                if (userId != -1) {
                    ScoreDAO scoreDAO = new ScoreDAO();
                    scoreDAO.insertScore(userId, this.score);
                    System.out.println("Score saved for user: " + currentUsername);
                }
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverScene.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setFinalScore(this.score);

            Stage stage = (Stage) questionLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the answer and automatically moves to the next question.
     *
     * @param event The ActionEvent from the answer button click.
     */
    @FXML
    private void handleAnswer(ActionEvent event) {
        if (currentQuestion == null) return;

        Button clickedButton = (Button) event.getSource();
        String selectedAnswer = clickedButton.getText();

        // 1. Feedback Logic
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score += 10;
            statusLabel.setText("Correct!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText("Incorrect!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }

        // 2. Auto-advance Logic: Move to the next question immediately
        currentIndex++;
        displayQuestion(currentIndex);
    }

    /**
     * Switches the scene back to the Dashboard.
     * Ensure the resource path matches the actual FXML filename in src/main/resources.
     */
    private void handleBackToDashboard() {
        try {
            System.out.println("Attempting to navigate back to Dashboard...");

            String fxmlFile = "LeaderBoardScene.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

            if (fxmlLoader.getLocation() == null) {
                System.err.println("Critical Error: Cannot find FXML file: " + fxmlFile);
                return;
            }

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnA.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Navigation successful!");

        } catch (IOException e) {
            System.err.println("Navigation failed due to IO error.");
            e.printStackTrace();
        }
    }
}
