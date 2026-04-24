package org.example.triviagame;

/**
 * Small data class which allows for a user entry object to be made from database, mainly for generating the table in AdminUserTools
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class UserEntry {
    private String username;
    private String role;

    public UserEntry(String username, String role){
        this.username = username;
        this.role = role;
    }

    public String getUsername(){
        return username;
    }

    public String getRole() {
        return role;
    }
}
