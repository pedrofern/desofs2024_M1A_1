package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.PasswordAlreadyReadException;

public class PasswordTest {

	@Test
    void testGetValue() {
        String passwordValue = "Test@1234";
        Password password = new Password(passwordValue);
        assertEquals(passwordValue, password.getValue());
    }

    @Test
    void testGetValueTwice() {
        String passwordValue = "Test@1234";
        Password password = new Password(passwordValue);
        assertEquals(passwordValue, password.getValue());
        assertThrows(PasswordAlreadyReadException.class, password::getValue);
    }

}
