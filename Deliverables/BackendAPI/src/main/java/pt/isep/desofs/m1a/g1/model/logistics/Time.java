package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidEmailFormatException;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Time {

    private static final Pattern TIME_PATTERN = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");

    private final String value;

    public Time(String value) {
        if (!isValid(value)) {
            throw new InvalidEmailFormatException("Invalid time format.");
        }
        this.value = value;
    }

    private static boolean isValid(String time) {
        return time != null && TIME_PATTERN.matcher(time).matches();
    }

}
