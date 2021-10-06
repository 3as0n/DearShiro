package core.util;

import java.util.UUID;

public class RandomID {
    public static String randomID() {
        return UUID.randomUUID().toString().substring(0, 12);
    }
}
