package org.example.triviagame;

/**
 * A small utility class to track which user/role session the game is currently in.
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class Session {
    private static String currentUser;
    private static String currentRole;

    public static void setUser(String user){
        currentUser = user;
    }

    public static String getUser(){
        return  currentUser;
    }

    public static void setRole(String role){
        currentRole = role;
    }

    public static String getRole(){
        return currentRole;
    }
}
