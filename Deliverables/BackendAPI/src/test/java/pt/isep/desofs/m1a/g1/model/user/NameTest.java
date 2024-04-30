package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

	@Test
	public void testNameConstructor_ValidName() {
		String validName = "John Doe";
		Name name = new Name(validName);
		assertEquals(validName, name.getValue());
	}

	@Test
	public void testNameConstructor_InvalidName_Null() {
		String invalidName = null;
		assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
	}

	@Test
	public void testNameConstructor_InvalidName_EmptyString() {
		String invalidName = "";
		assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
	}

	@Test
	public void testNameConstructor_InvalidName_InvalidCharacters() {
		String invalidName = "John Doe123";
		assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
	}

}
