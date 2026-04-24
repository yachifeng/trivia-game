package org.example.triviagame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 5. getUserRoles
     * It will retrieve the user's role from the database and return it.
     *
     * @param username and password of the username to retrieve the role.
     *
     */
    public String getUserRole(String username, String password) {
        String sql = "SELECT role FROM USERS WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // if user is not found
    }


    /**
     * KMB
     * 6. getUserIdByUsername
     * pulls user_id from the user table via username, to be used in leaderboard entry deletion.
     * Needed because the "SCORES" table does not have a "Username" column
     * @param username
     * @return
     */
    public int getUserIdByUsername(String username) {
        String sql = "SELECT id FROM USERS WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // if user is not found
    }

    /**
     * KMB
     * 7. getAllUsers
     * pulls every user from the USERS table
     */
    public List<UserEntry> getAllUsers(){
        List<UserEntry> users = new ArrayList<>();
        String sql = "SELECT username, role FROM USERS";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                String username = rs.getString("username");
                String role = rs.getString("role");

                users.add(new UserEntry(username, role));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    /**
     * KMB
     * 8. updateRole
     * Allows admins to promote/demote a user
     */
    public void updateRole(String username, String role){
        String sql = "UPDATE USERS SET role = ? WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, role);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}