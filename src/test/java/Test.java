import com.sun.media.sound.SoftTuning;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().substring(0, 12));
    }
}
