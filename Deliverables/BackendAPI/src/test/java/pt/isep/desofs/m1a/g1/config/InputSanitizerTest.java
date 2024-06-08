package pt.isep.desofs.m1a.g1.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputSanitizerTest {

    @Test
    public void testSanitize() {
        String input = "<script>alert('XSS');</script>";
        String sanitized = InputSanitizer.sanitize(input);
        assertNotEquals(input, sanitized);
        assertFalse(sanitized.contains("<script>"));
    }

    @Test
    public void testSanitizeSafeInput() {
        String input = "Hello, world!";
        String sanitized = InputSanitizer.sanitize(input);
        assertEquals(input, sanitized);
    }

    @Test
    public void testContainsMaliciousContentNullInput() {
        assertFalse(InputSanitizer.containsMaliciousContent(null));
    }

    @Test
    public void testContainsMaliciousContentEmptyInput() {
        assertFalse(InputSanitizer.containsMaliciousContent(""));
    }

    @Test
    public void testContainsMaliciousContentSQLInjection() {
        String input = "SELECT * FROM users WHERE username='admin'; --";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentXSS() {
        String input = "<script>alert('XSS');</script>";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentDangerousHTMLTags() {
        String input = "<iframe src='http://example.com'></iframe>";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentSafeInput() {
        String input = "Hello, world!";
        assertFalse(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentSafeHTML() {
        String input = "<b>Bold Text</b>";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentSQLKeywordWithinSafeInput() {
        String input = "This is a select few items.";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentDoubleQuote() {
        String input = "Hello;--, world!";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContentAmpersand() {
        String input = "Hello&, world!";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }
    
}
