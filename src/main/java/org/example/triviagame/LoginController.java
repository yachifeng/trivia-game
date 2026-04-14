package org.example.triviagame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for the login scene,
 * handling user authentication and registration via UserDAO.
 *
 * @author Yachi Feng
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
            statusLabel.setText("Login Successful!");
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

}

