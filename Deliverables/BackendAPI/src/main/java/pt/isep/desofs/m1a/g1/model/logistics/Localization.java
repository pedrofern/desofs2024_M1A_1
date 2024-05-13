package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Localization {

    private final int x;
    private final int y;
    private final int z;

    public Localization(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private static boolean isValid(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0;
    }
}
