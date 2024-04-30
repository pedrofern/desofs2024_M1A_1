package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void testValidEmail() {
        String validEmail = "test@example.com";
        Email email = new Email(validEmail);
        assertEquals(validEmail, email.getValue());
    }
    
    @Test
    public void testInvalidEmail() {
        String invalidEmail = "invalid-email";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }
    
    @Test
    public void testNullEmail() {
        String nullEmail = null;
        assertThrows(IllegalArgumentException.class, () -> new Email(nullEmail));
    }
    
}
