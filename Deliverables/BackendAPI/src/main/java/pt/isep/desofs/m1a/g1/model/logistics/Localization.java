package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidLocalizationFormatException;

@Getter
@EqualsAndHashCode
public class Localization {

    private final int x;
    private final int y;
    private final int z;
    private static final int MAX_INT = 100;

    public Localization(int x, int y, int z) {
        if (!isValid(x, y, z)) {
            throw new InvalidLocalizationFormatException("Invalid localization.");
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private static boolean isValid(int x, int y, int z) {
        return x >= 0 && x <= MAX_INT && y >= 0 && y <= MAX_INT && z >= 0 && z <= MAX_INT;
    }
}
