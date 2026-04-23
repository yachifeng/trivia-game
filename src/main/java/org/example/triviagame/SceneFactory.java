package org.example.triviagame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * A scene factory to handle the various scenes within the game.
 *
 * @author KMB, Yachi Feng
 * @version 0.2.0
 * @since 4/22/2026
 */
public class SceneFactory {

    public static Scene create(SceneType type){
        return switch (type){
            case LOGIN -> loadScene("/org/example/triviagame/LoginScene.fxml", "Login");
            case ADMINTOOLS -> loadScene("/org/example/triviagame/AdminScene.fxml", "Admin Tools");
            case LEADERBOARD -> loadScene("/org/example/triviagame/LeaderboardScene.fxml", "Leaderboard");
            case USERMANAGETOOLS -> loadScene("/org/example/triviagame/UserManagementScene.fxml", "User Tools");
            case QUESTIONTOOLS -> loadScene("/org/example/triviagame/QuestionToolsScene.fxml", "Question Tools");
            case BOARDMANAGETOOLS -> loadScene("/org/example/triviagame/LeaderBoardManagementScene.fxml", "Board Management");
            case GAMEPLAY -> loadScene("/org/example/triviagame/GamePlayScene.fxml", "Gameplay");
            case GAMEOVER -> loadScene("/org/example/triviagame/GameOverScene.fxml", "Game Over");
            case TITLE -> loadScene("/org/example/triviagame/TitleScene.fxml", "Title");
        };
    }

    /**
     * Helper method to reduce code duplication when loading FXML files.
     * @param fxmlPath The path to the FXML resource.
     * @param sceneName The display name of the scene for error logging.
     * @return A new Scene object containing the loaded FXML root.
     */
    private static Scene loadScene(String fxmlPath, String sceneName) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneFactory.class.getResource(fxmlPath));
            Parent root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            System.err.println("Failed to load " + sceneName + " Scene from: " + fxmlPath);
            throw new RuntimeException(e);
        }
    }
}
