package org.example.triviagame;


import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

/**
 * a reusable scene switcher for various purposes.
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class SceneSwitcher {
    public static void switchScene(ActionEvent event, SceneType type) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneFactory.create(type));
    }
}
