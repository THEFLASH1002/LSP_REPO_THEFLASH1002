package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generation strategy that uses java.util.Random
 * and generates passwords containing digits only (0-9).
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {
    private static final String DIGITS = "0123456789";
    private Random random;

    /**
     * Constructs a new BasicPasswordStrategy instance.
     */
    public BasicPasswordStrategy() {
        this.random = new Random();
    }

    /**
     * Generates a password containing only digits (0-9).
     *
     * @param length the desired length of the password
     * @return a password string containing only digits
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}

