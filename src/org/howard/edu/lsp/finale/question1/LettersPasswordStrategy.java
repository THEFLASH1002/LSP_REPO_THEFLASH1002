package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters-only password generation strategy that generates passwords
 * containing only letters (A-Z, a-z).
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    private Random random;

    /**
     * Constructs a new LettersPasswordStrategy instance.
     */
    public LettersPasswordStrategy() {
        this.random = new Random();
    }

    /**
     * Generates a password containing only letters (A-Z, a-z).
     *
     * @param length the desired length of the password
     * @return a password string containing only letters
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}

