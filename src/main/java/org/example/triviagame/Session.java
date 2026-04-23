package org.example.triviagame;

/**
 * A small class to track which user session the game is currently in.
 *
 * @author KMB
 * @version 0.1.0
 * @since 4/23/2026
 */
public class Session {
    private static String currentUser;

    public static void setUser(String user){
        currentUser = user;
    }

    public static String getUser(){
        return  currentUser;
    }
}
