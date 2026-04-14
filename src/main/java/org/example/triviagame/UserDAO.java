package org.example.triviagame;
import java.sql.*;

/**
 * Provides Data Access Object (DAO) methods for managing user-related operations,
 * including registration and authentication, within the persistent database layer.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/14/26
 */
public class UserDAO {

    /**
     * 1. Create (Insert)
     * Registers a new user with basic input validation.
     *
     * @param username The unique username for the new account.
     * @param password The user's chosen password.
     * @param role The assigned role (e.g., 'admin' or 'user').
     * @return true if registration is successful; false if input is blank or an error occurs.
     */
    public boolean registerUser(String username, String password, String role) {
        // Safety checks to prevent blank data
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                role == null || role.isBlank()) {
            return false;
        }

        String sql = "INSERT INTO USERS(username, password, role) VALUES(?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false; // to prevent duplicate username
        }
    }

}