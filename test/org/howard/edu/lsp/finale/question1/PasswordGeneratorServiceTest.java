package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // TODO: verify that 'service' is not null
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // TODO: Verify that both 'service' (created in @BeforeEach) 
        // and 'second' refer to the EXACT same object in memory. This 
        // test must confirm true singleton behavior — not just that the 
        // two objects are equal, but they are the *same 
        // instance* returned by getInstance().
        assertSame(service, second, "Both instances should refer to the exact same object (singleton behavior)");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // TODO: verify correct exception behavior for each algorithm
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        }, "Should throw IllegalStateException when generatePassword is called before setAlgorithm");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(10, p.length(), "Password should have the correct length");
        assertTrue(p.matches("[0-9]+"), "Password should contain only digits (0-9)");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(12, p.length(), "Password should have the correct length");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Password should contain only A-Z, a-z, and 0-9");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        // TODO: verify required behavior
        assertNotNull(p, "Password should not be null");
        assertEquals(8, p.length(), "Password should have the correct length");
        assertTrue(p.matches("[A-Za-z]+"), "Password should contain only letters (A-Z, a-z)");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // TODO: verify correct behavior characteristics of each algorithm
        // Verify basic algorithm produces digits only
        assertTrue(p1.matches("[0-9]+"), "Basic algorithm should produce digits only");
        assertEquals(10, p1.length(), "Basic password should have correct length");
        
        // Verify letters algorithm produces letters only
        assertTrue(p2.matches("[A-Za-z]+"), "Letters algorithm should produce letters only");
        assertEquals(10, p2.length(), "Letters password should have correct length");
        
        // Verify enhanced algorithm produces mixed characters
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced algorithm should produce A-Z, a-z, and 0-9");
        assertEquals(10, p3.length(), "Enhanced password should have correct length");
        
        // Verify that algorithms produce different results (behavior changed)
        // Note: There's a small chance passwords could be the same, but with different
        // character sets, this is extremely unlikely. We verify the character sets are different.
        assertNotEquals(p1, p2, "Basic and Letters algorithms should produce different passwords");
        assertNotEquals(p2, p3, "Letters and Enhanced algorithms should produce different passwords");
    }
}

