package pt.isep.desofs.m1a.g1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;

public class PasswordValidatorTest {

	 @Test
	    void testCreateValidPassword() {
	        String validPassword = "Test@1234";
	        assertEquals(validPassword, PasswordValidator.createPassword(validPassword));
	    }

	    @Test
	    void testCreateInvalidPassword() {
	        String invalidPassword = "weak";
	        assertThrows(InvalidPasswordFormatException.class, () -> PasswordValidator.createPassword(invalidPassword));
	    }
	
}
