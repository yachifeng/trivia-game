package org.example.triviagame;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static org.example.triviagame.SceneSwitcher.switchScene;


/**
 * [Brief Description of this class]
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class UserManagementController {
    UserDAO users = new UserDAO();

    @FXML
    private void handleGoBack(ActionEvent event) {
        switchScene(event, SceneType.ADMINTOOLS);
    }

    @FXML
    private void handlePromote(){
        UserEntry selected = userTable.getSelectionModel().getSelectedItem();

        if(selected == null){
            System.out.println("No user selected");
            return;
        }else if(selected.getRole().equals("admin")){
            System.out.println("Cannot modify an admin's role");
            return;
        }

        users.updateRole(selected.getUsername(), "moderator");
        loadUserData();
    }

    @FXML
    private void handleDemote(){
        UserEntry selected = userTable.getSelectionModel().getSelectedItem();

        if(selected == null){
            System.out.println("No user selected");
            return;
        }else if(selected.getRole().equals("admin")){
            System.out.println("Cannot modify an admin's role");
            return;
        }

        users.updateRole(selected.getUsername(), "user");
        loadUserData();
    }

    @FXML
    private void handleDelete(){
        UserEntry selected = userTable.getSelectionModel().getSelectedItem();

        if(selected == null){
            System.out.println("No user selected");
            return;
        }else if(selected.getRole().equals("admin")){
            System.out.println("Cannot delete an admin.");
            return;
        }

        users.deleteUser(selected.getUsername());
        loadUserData();
    }

    @FXML
    TableView<UserEntry> userTable;

    @FXML
    // This will store the table's column which contains the usernames.
    private TableColumn<UserEntry, String> usernameColumn;

    @FXML
    // This will store the table's column which contains the user's role.
    private TableColumn<UserEntry, String> roleColumn;


    private void loadUserData(){
        List<UserEntry> data = users.getAllUsers();
        userTable.setItems(FXCollections.observableArrayList(data));
    }

    @FXML
    public void initialize(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadUserData();
    }

}
