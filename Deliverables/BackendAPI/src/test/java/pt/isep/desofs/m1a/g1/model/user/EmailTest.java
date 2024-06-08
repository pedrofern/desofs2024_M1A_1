package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.InvalidEmailFormatException;

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
        assertThrows(InvalidEmailFormatException.class, () -> new Email(invalidEmail));
    }
    
    @Test
    public void testNullEmail() {
        String nullEmail = null;
        assertThrows(InvalidEmailFormatException.class, () -> new Email(nullEmail));
    }
    
	@Test
    public void testEqualsAndHashCode() {
        String emailValue = "test@example.com";
        Email email1 = new Email(emailValue);
        Email email2 = new Email(emailValue);
        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());    
	}
    
}
