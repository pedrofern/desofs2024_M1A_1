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
    public void testContainsMaliciousContent_NullInput() {
        assertFalse(InputSanitizer.containsMaliciousContent(null));
    }

    @Test
    public void testContainsMaliciousContent_EmptyInput() {
        assertFalse(InputSanitizer.containsMaliciousContent(""));
    }

    @Test
    public void testContainsMaliciousContent_SQLInjection() {
        String input = "SELECT * FROM users WHERE username='admin'; --";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContent_XSS() {
        String input = "<script>alert('XSS');</script>";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContent_DangerousHTMLTags() {
        String input = "<iframe src='http://example.com'></iframe>";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContent_SafeInput() {
        String input = "Hello, world!";
        assertFalse(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContent_SafeHTML() {
        String input = "<b>Bold Text</b>";
        assertFalse(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testContainsMaliciousContent_SQLKeywordWithinSafeInput() {
        String input = "This is a select few items.";
        assertTrue(InputSanitizer.containsMaliciousContent(input));
    }

    @Test
    public void testSanitizeInputForScripts() {
        String input = "<script>alert('Hello');</script>";
        String expectedOutput = "&amp;lt;&amp;#115cript&amp;gt;alert(&amp;#39;Hello&amp;#39;);&amp;lt;/&amp;#115cript&amp;gt;";
        assertEquals(expectedOutput, InputSanitizer.sanitizeInputForScripts(input));
    }
}
