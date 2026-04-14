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


}