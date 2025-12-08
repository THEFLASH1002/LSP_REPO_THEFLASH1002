package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generation strategy that uses java.security.SecureRandom
 * and generates passwords containing uppercase letters, lowercase letters, and digits.
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    private SecureRandom secureRandom;

    /**
     * Constructs a new EnhancedPasswordStrategy instance.
     */
    public EnhancedPasswordStrategy() {
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generates a password containing uppercase letters, lowercase letters, and digits.
     *
     * @param length the desired length of the password
     * @return a password string containing A-Z, a-z, and 0-9
     */
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}

