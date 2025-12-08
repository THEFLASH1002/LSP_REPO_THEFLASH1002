package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 * This interface allows different password generation strategies
 * to be implemented and swapped at runtime.
 */
public interface PasswordGenerationStrategy {
    /**
     * Generates a password of the specified length using the strategy's algorithm.
     *
     * @param length the desired length of the password
     * @return a password string generated according to the strategy
     */
    String generatePassword(int length);
}

