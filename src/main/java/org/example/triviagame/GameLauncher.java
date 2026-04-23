package org.example.triviagame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Tester for running the scenes. Currently, requires manually changing the scene in code.
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/12/2026
 */
public class GameLauncher extends Application {
    private static final String TITLE = "Title Screen";

    @Override
    public void start(Stage stage) {

        // Initializes and populates database with sample data <- Remove both lines whenever necessary
        DatabaseManager.initializeDatabase();
        DatabaseManager.seedTestData();

        stage.setTitle(TITLE);
        stage.setScene(SceneFactory.create(SceneType.LOGIN)); // <-- Change scene to load here
        stage.show();
    }
    /**
     * Application entry point. JavaFX requires calling launch(), which
     * internally creates the JavaFX runtime and calls start().
     */
    public static void main(String[] args) {
        launch(args);
    }
}
