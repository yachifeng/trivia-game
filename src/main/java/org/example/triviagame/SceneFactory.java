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
            case GAMEPLAY -> loadScene("/org/example/triviagame/GamePlayScene.fxml", "Gameplay");
            case GAMEOVER -> loadScene("/org/example/triviagame/GameOverScene.fxml", "Game Over");
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


    /**
     * Builds the scene for login/register. Pulls the scene information from LoginScene.fxml
     * @return the working scene

    private static Scene buildLogin(){

        FXMLLoader loginLoader = new FXMLLoader(SceneFactory.class.getResource("/org/example/triviagame/LoginScene.fxml"));
        try{
            System.out.println(SceneFactory.class.getResource("/org/example/triviagame/LoginScene.fxml"));
            Parent root = loginLoader.load();
            return new Scene(root);
        } catch(IOException e){
            System.out.println("Failed to load Login Scene");
            throw new RuntimeException(e);
        }
    }

    private static Scene buildAdminTools(){

        FXMLLoader loginLoader = new FXMLLoader(SceneFactory.class.getResource("/org/example/triviagame/AdminScene.fxml"));
        try{
            System.out.println(SceneFactory.class.getResource("/org/example/triviagame/AdminScene.fxml"));
            Parent root = loginLoader.load();
            return new Scene(root);
        } catch(IOException e){
            System.out.println("Failed to load Login Scene");
            throw new RuntimeException(e);
        }
    }

    private static Scene buildLeaderboard() {
        FXMLLoader loginLoader = new FXMLLoader(SceneFactory.class.getResource("/org/example/triviagame/LeaderboardScene.fxml"));
        try {
            System.out.println(SceneFactory.class.getResource("/org/example/triviagame/LeaderboardScene.fxml"));
            Parent root = loginLoader.load();
            return new Scene(root);
        } catch (IOException e) {
            System.out.println("Failed to load Leaderboard Scene");
            throw new RuntimeException(e);
        }
    }
    */
}
