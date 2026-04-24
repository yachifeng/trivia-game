package org.example.triviagame;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Small data class which allows for a user entry object to be made from database, mainly for generating the table in AdminUserTools
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class UserEntry {
    private final StringProperty username;
    private final StringProperty role;

    public UserEntry(String username, String role){
        this.username = new SimpleStringProperty(username);
        this.role = new SimpleStringProperty(role);
    }

    public String getUsername(){
        return username.get();
    }

    public StringProperty usernameProperty(){
        return username;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role){
        this.role.set(role);
    }

    public StringProperty roleProperty(){
        return role;
    }
}
