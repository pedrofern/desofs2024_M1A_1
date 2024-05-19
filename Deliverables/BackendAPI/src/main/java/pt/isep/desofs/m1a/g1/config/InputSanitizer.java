package pt.isep.desofs.m1a.g1.config;

import org.owasp.encoder.Encode;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class InputSanitizer {

    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public static String sanitize(String input) {
        return POLICY.sanitize(input);
    }

    public static boolean containsMaliciousContent(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        String lowerCaseInput = input.toLowerCase();

        // SQL Injection patterns
        String[] sqlKeywords = {"select ", "insert ", "update ", "delete ", "drop ", "truncate ", "exec ", "union ", "create ", "alter ", "rename ", "--", ";--", ";", "/*", "*/", "@@", "@"};
        for (String keyword : sqlKeywords) {
            if (lowerCaseInput.contains(keyword)) {
                return true;
            }
        }

        // XSS patterns
        String[] xssKeywords = {"<script>", "</script>", "javascript:", "onload=", "onerror=", "onmouseover=", "alert(", "eval(", "expression(", "vbscript:", "mocha:", "livescript:"};
        for (String keyword : xssKeywords) {
            if (lowerCaseInput.contains(keyword)) {
                return true;
            }
        }

        // Dangerous HTML tags
        String[] dangerousTags = {"<iframe>", "</iframe>", "<object>", "</object>", "<embed>", "</embed>", "<link>", "</link>", "<style>", "</style>", "<meta>", "</meta>", "<form>", "</form>", "<input>", "</input>"};
        for (String tag : dangerousTags) {
            if (lowerCaseInput.contains(tag)) {
                return true;
            }
        }

        // Command Injection patterns
        String[] commandKeywords = {"&", "|", ";", "$(", "`", "||", "&&"};
        for (String keyword : commandKeywords) {
            if (lowerCaseInput.contains(keyword)) {
                return true;
            }
        }

        return false;
    }

    public static String sanitizeInputForScripts(String input) {

        // Replace characters that may be interpreted as HTML or JavaScript with their HTML entity equivalents
        input = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;")
                .replaceAll("script", "&#115cript");

        return Encode.forHtml(input);

    }
}