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

    /**
     * 2. Read
     * Validates user credentials by checking if the username and password match a record in the database.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if a matching record is found; false otherwise.
     */
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 3. Update
     * Updates the password for a specific user in the database.
     *
     * @param username The username of the account to be updated.
     * @param newPassword The new password to be set for the user.
     */
    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE USERS SET password = ? WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4. Delete
     * Permanently removes a user record from the database based on their username.
     *
     * @param username The username of the account to be deleted.
     */
    public void deleteUser(String username) {
        String sql = "DELETE FROM USERS WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}