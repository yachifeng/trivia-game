package org.example.triviagame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardEntryTest {

    @Test
    void leaderboardEntryGetters() {
        LeaderboardEntry entry = new LeaderboardEntry(1, "Anthony", 100);

        assertEquals(1, entry.getRank());
        assertEquals("Anthony", entry.getUsername());
        assertEquals(100, entry.getScore());
    }

    @Test
    void leaderboardEntrySetters() {
        LeaderboardEntry entry = new LeaderboardEntry(3, "Yachi", 70);

        entry.scoreProperty().set(85);
        entry.rankProperty().set(5);
        entry.usernameProperty().set("Updated");

        assertEquals(5, entry.getRank());
        assertEquals("Updated", entry.getUsername());
        assertEquals(85, entry.getScore());
    }

    @Test
    void leaderboardEntryProperties() {
        LeaderboardEntry entry = new LeaderboardEntry(2, "Kaleb", 90);

        assertEquals(2, entry.rankProperty().get());
        assertEquals("Kaleb", entry.usernameProperty().get());
        assertEquals(90, entry.scoreProperty().get());
    }

    @Test
    void handleZeros() {
        LeaderboardEntry entry = new LeaderboardEntry(1, "TestUser", 0);

        assertEquals(0, entry.getScore());
    }

    @Test
    void handleNegatives() {
        LeaderboardEntry entry = new LeaderboardEntry(1, "TestUser", -10);

        assertEquals(-10, entry.getScore());
    }

    @Test
    void handleEmpty() {
        LeaderboardEntry entry = new LeaderboardEntry(1, "", 50);

        assertEquals("", entry.getUsername());
    }
}