package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.InvalidNameFormatException;

public class NameTest {

	@Test
	public void testNameConstructorValidName() {
		String validName = "John Doe";
		Name name = new Name(validName);
		assertEquals(validName, name.getValue());
	}

	@Test
	public void testNameConstructorInvalidNameNull() {
		String invalidName = null;
		assertThrows(InvalidNameFormatException.class, () -> new Name(invalidName));
	}

	@Test
	public void testNameConstructorInvalidNameEmptyString() {
		String invalidName = "";
		assertThrows(InvalidNameFormatException.class, () -> new Name(invalidName));
	}

	@Test
	public void testNameConstructorInvalidNameInvalidCharacters() {
		String invalidName = "John Doe123";
		assertThrows(InvalidNameFormatException.class, () -> new Name(invalidName));
	}
	
	@Test
	public void testEqualsAndHashCode() {
		String nameValue = "John Doe";
		Name name1 = new Name(nameValue);
		Name name2 = new Name(nameValue);
		assertEquals(name1, name2);
		assertEquals(name1.hashCode(), name2.hashCode());
	}

}
