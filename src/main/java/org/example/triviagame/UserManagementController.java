package org.example.triviagame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static org.example.triviagame.SceneSwitcher.switchScene;


/**
 * Controller for the Admin User Tools
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class UserManagementController {

    private final ObservableList<UserEntry> userData = FXCollections.observableArrayList();

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
        selected.setRole("moderator");
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
        selected.setRole("user");
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
        userData.remove(selected);
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
        userData.setAll(data);
    }

    @FXML
    public void initialize(){
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        userTable.setItems(userData);
        loadUserData();
    }

}
