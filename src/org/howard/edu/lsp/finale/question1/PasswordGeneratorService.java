package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * PasswordGeneratorService provides a centralized service for generating passwords
 * using various algorithms. This service implements the Singleton pattern to ensure
 * only one instance exists, and uses the Strategy pattern to allow runtime selection
 * and swapping of password generation algorithms.
 * 
 * <p>
 * DESIGN PATTERNS USED:
 * ====================
 * 
 * 1. SINGLETON PATTERN:
 *    - The service maintains a single instance accessible via getInstance().
 *    - This ensures a single shared access point for password generation across
 *      the application, preventing multiple instances and maintaining consistent state.
 * 
 * 2. STRATEGY PATTERN:
 *    - Password generation algorithms are encapsulated in separate strategy classes
 *      (BasicPasswordStrategy, EnhancedPasswordStrategy, LettersPasswordStrategy)
 *      that implement the PasswordGenerationStrategy interface.
 *    - The service can swap algorithms at runtime using setAlgorithm(String name).
 *    - This pattern allows for future algorithm expansion without modifying client code
 *      or the service itself - new algorithms can be added by creating new strategy
 *      classes and registering them in the algorithm map.
 * 
 * WHY THESE PATTERNS WERE APPROPRIATE:
 * ====================================
 * 
 * The Singleton pattern was appropriate because:
 * - The requirement explicitly states "Only one instance of the service may exist"
 * - It provides the "single shared access point" requirement
 * - It ensures consistent behavior across the application
 * 
 * The Strategy pattern was appropriate because:
 * - It directly addresses the requirement to "support multiple approaches to password generation"
 * - It enables "runtime selection" of algorithms via setAlgorithm()
 * - It supports "future expansion" - new algorithms can be added by implementing
 *   the PasswordGenerationStrategy interface and registering them, without modifying
 *   existing code (Open/Closed Principle)
 * - It makes password-generation behavior "swappable" as required
 * - Client code remains unchanged when new algorithms are added
 */
public class PasswordGeneratorService {
    private static PasswordGeneratorService instance;
    private PasswordGenerationStrategy currentStrategy;
    private Map<String, PasswordGenerationStrategy> algorithms;

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes the algorithm map with supported strategies.
     */
    private PasswordGeneratorService() {
        this.algorithms = new HashMap<>();
        this.algorithms.put("basic", new BasicPasswordStrategy());
        this.algorithms.put("enhanced", new EnhancedPasswordStrategy());
        this.algorithms.put("letters", new LettersPasswordStrategy());
    }

    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * Creates the instance if it does not exist (lazy initialization).
     *
     * @return the singleton instance of PasswordGeneratorService
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            synchronized (PasswordGeneratorService.class) {
                if (instance == null) {
                    instance = new PasswordGeneratorService();
                }
            }
        }
        return instance;
    }

    /**
     * Sets the password generation algorithm to use.
     * Supported algorithms: "basic", "enhanced", "letters"
     *
     * @param name the name of the algorithm to use
     * @throws IllegalArgumentException if the algorithm name is not supported
     */
    public void setAlgorithm(String name) {
        PasswordGenerationStrategy strategy = algorithms.get(name);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        this.currentStrategy = strategy;
    }

    /**
     * Generates a password of the specified length using the currently selected algorithm.
     *
     * @param length the desired length of the password
     * @return a password string generated according to the current algorithm
     * @throws IllegalStateException if no algorithm has been set before calling this method
     */
    public String generatePassword(int length) {
        if (currentStrategy == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        return currentStrategy.generatePassword(length);
    }
}

