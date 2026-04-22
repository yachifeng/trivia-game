package org.example.triviagame;

/**
 * Model class representing a single trivia question.
 * Used to transfer data from the database to the UI.
 *
 * @author Yachi Feng
 * @version 21.0.10
 * @since 4/22/26
 */

public class Question {
    private String text;
    private String[] options = new String[4];
    private String correctAnswer;

    /**
     * Constructs a new Question object with the specified text, options, and correct answer.
     *
     * @param text   The main question text.
     * @param a      Text for option A.
     * @param b      Text for option B.
     * @param c      Text for option C.
     * @param d      Text for option D.
     * @param answer The string representing the correct answer (e.g., "A", "B", "C", or "D").
     */
    public Question(String text, String a, String b, String c, String d, String answer) {
        this.text = text;
        this.options[0] = a;
        this.options[1] = b;
        this.options[2] = c;
        this.options[3] = d;
        this.correctAnswer = answer;
    }

    /**
     * Gets the text of the trivia question.
     * @return The question content as a {@link String}.
     */
    public String getText() { return text; }

    /**
     * Gets an array containing all four multiple-choice options.
     * The order follows: [0]=A, [1]=B, [2]=C, [3]=D.
     * @return A {@link String} array of options.
     */
    public String[] getOptions() { return options; }

    /**
     * Gets the correct answer for this question.
     * @return The correct answer value (e.g., the text of the correct option or its key).
     */
    public String getCorrectAnswer() { return correctAnswer; }
}
