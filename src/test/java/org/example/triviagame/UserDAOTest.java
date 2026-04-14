package org.example.triviagame;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides unit tests for the UserDAO class to ensure database
 * CRUD operations for user entities are functioning correctly.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/14/26
 */

public class UserDAOTest {
    private UserDAO userDAO = new UserDAO();

    /**
     * Set up the test environment by initializing the database
     * before any tests run.
     */
    @BeforeAll
    public static void setup() {
        DatabaseManager.initializeDatabase();
    }

    /**
     * Cleans up the test data after each test method to ensure
     * a fresh environment for subsequent tests.
     */
    @AfterEach
    public void tearDown() {
        // Clean up test users to maintain a clean database state
        userDAO.deleteUser("testUser");
        userDAO.deleteUser("deleteMe");
        userDAO.deleteUser("updateUser");
    }

    /**
     * Tests the user registration (Insert) and login validation (Read)
     * to ensure credentials are correctly stored and retrieved.
     */
    @Test
    public void testInsertAndLogin() {
        // test Insert (for ID: O02)
        userDAO.registerUser("testUser", "password123", "user");

        // test Read
        boolean loginSuccess = userDAO.validateLogin("testUser", "password123");
        assertTrue(loginSuccess, "Login should succeed for registered user.");
    }

    /**
     * Tests the update functionality (Update - O02 requirement) to ensure
     * user information can be modified successfully in the database.
     */
    @Test
    public void testUpdateUserPassword() {
        // 1. Create a new user (Insert - O02 Requirement)
        userDAO.registerUser("updateUser", "oldPass", "user");

        // 2. Update the user's password (Update - O02 Requirement)
        userDAO.updatePassword("updateUser", "newPass");

        // 3. Verify old password fails and new password succeeds (Read/Validation)
        assertFalse(userDAO.validateLogin("updateUser", "oldPass"), "Old password should not work.");
        assertTrue(userDAO.validateLogin("updateUser", "newPass"), "New password should be valid.");
    }

    /**
     * Tests the user deletion functionality to ensure a user
     * can no longer log in after being removed from the database.
     */
    @Test
    public void testDeleteUser() {
        // test Delete (for ID: O02)
        userDAO.registerUser("deleteMe", "123", "user");
        userDAO.deleteUser("deleteMe");
        assertFalse(userDAO.validateLogin("deleteMe", "123"), "User should not exist after deletion.");
    }

    /**
     * Tests the input validation logic (Security improvement) to ensure
     * that blank or null values are not allowed for user registration.
     */
    @Test
    public void testInvalidRegistrationInputs() {
        // Test null values
        assertFalse(userDAO.registerUser(null, "pass", "user"), "Should fail for null username.");

        // Test empty strings
        assertFalse(userDAO.registerUser("", "pass", "user"), "Should fail for empty username.");

        // Test blank strings (whitespace only)
        assertFalse(userDAO.registerUser("   ", "pass", "user"), "Should fail for blank username.");

        // Test null/empty password
        assertFalse(userDAO.registerUser("validUser", null, "user"), "Should fail for null password.");
        assertFalse(userDAO.registerUser("validUser", "", "user"), "Should fail for empty password.");
    }
}