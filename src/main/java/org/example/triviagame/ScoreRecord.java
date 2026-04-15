package org.example.triviagame;



/**
 * @author Anthony Ou
 * @version 0.1.0
 * @since 4/14/26
 *
 * ScoreRecord Class represents the quiz history record containing a score and the date taken.
 *
 */

public class ScoreRecord {

    private final int score;
    private final String dateTaken;

    public ScoreRecord(int score, String dateTaken) {
        this.score = score;
        this.dateTaken = dateTaken;
    }

    public int getScore() {
        return score;
    }

    public String getDateTaken() {
        return dateTaken;
    }

}
