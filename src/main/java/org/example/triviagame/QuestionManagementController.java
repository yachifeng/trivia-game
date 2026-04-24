package org.example.triviagame;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static org.example.triviagame.SceneSwitcher.switchScene;

/**
 * Controller for the Admin/Moderator Question Tools
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class QuestionManagementController {

    QuestionDAO questions = new QuestionDAO();

    @FXML
    private void handleGoBack(ActionEvent event) {
        if(Session.getRole().equals("admin")){
            switchScene(event, SceneType.ADMINTOOLS);
        } else {
            switchScene(event, SceneType.MODERATORTOOLS);
        }
    }

    @FXML
    private TableView<Question> questionTable;

    @FXML
    private TableColumn<Question, String> answerColumn;

    @FXML
    private TableColumn<Question, String> questionColumn;

    @FXML
    private TextField questionField;

    @FXML
    private TextField optionAField;

    @FXML
    private TextField optionBField;

    @FXML
    private TextField optionCField;

    @FXML
    private TextField optionDField;

    @FXML
    private TextField correctAnswerField;

    @FXML
    private void handleCreateQuestion(){
        String questionText = questionField.getText();
        String optionA = optionAField.getText();
        String optionB = optionBField.getText();
        String optionC = optionCField.getText();
        String optionD = optionDField.getText();
        String correctAnswer = correctAnswerField.getText();

        if (questionText.isBlank() || optionA.isBlank() || optionB.isBlank() || optionC.isBlank() || optionD.isBlank() || correctAnswer.isBlank()) {
            System.out.println("Please fill in all fields to create a question.");
            return;
        }

        questions.insertQuestion(
                1, //categories are not established yet, so all questions will just be in default category 1
                questionText,
                optionA,
                optionB,
                optionC,
                optionD,
                correctAnswer
        );

        loadQuestions();
        clearCreateFields();
    }

    @FXML
    private void handleDeleteQuestion(){
        Question selected = questionTable.getSelectionModel().getSelectedItem();

        if(selected == null){
            System.out.println("No question selected");
            return;
        }

        questions.deleteQuestionByText(selected.getText());
        loadQuestions();
    }

    private void clearCreateFields() {
        questionField.clear();
        optionAField.clear();
        optionBField.clear();
        optionCField.clear();
        optionDField.clear();
        correctAnswerField.clear();
    }

    private void loadQuestions(){
        List<Question> data = questions.getAllQuestions();
        questionTable.setItems(FXCollections.observableArrayList(data));
    }

    @FXML
    public void initialize(){
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        loadQuestions();
    }

}
