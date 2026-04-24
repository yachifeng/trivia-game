package org.example.triviagame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for the login scene,
 * handling user authentication and registration via UserDAO.
 *
 * @author Yachi Feng, Anthony Ou
 * @version 21.0.10
 * @since 4/14/26
 */
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final UserDAO userDAO = new UserDAO();

    /**
     * Handles the login button action by validating user credentials against the database.
     * Displays success or error messages to the user via the status label.
     */
    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        if (userDAO.validateLogin(user, pass)) {
            statusLabel.setText("You have successfully logged in. Welcome back!");
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            statusLabel.setText("Incorrect username or password. Please try again.");
        }
    }

    /**
     * Handles the register button action by creating a new user account in the database.
     * Assigns a default role of "user" to new registrations and provides status feedback.
     */
    @FXML
    private void handleRegister() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        if (userDAO.registerUser(user, pass, "user")) {
            statusLabel.setText("Registration Successful!");
            statusLabel.setTextFill(javafx.scene.paint.Color.BLUE);
        } else {
            statusLabel.setText("Registration failed.");
        }
    }

    /**
     * Displays message when user chooses to return to main menu.
     */
    @FXML
    private void handleReturnMenu(ActionEvent event) {
        System.out.println("Returning to Main Menu. Please select your option.");
    }
}

