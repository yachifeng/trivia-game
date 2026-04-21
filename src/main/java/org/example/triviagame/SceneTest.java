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
public class SceneTest extends Application {
    private static final String TITLE = "Scene Tester ";

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setScene(SceneFactory.create(SceneType.ADMINTOOLS)); // <-- Change scene to load here
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
