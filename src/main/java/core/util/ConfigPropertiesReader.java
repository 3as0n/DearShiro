package core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesReader {
    private static Properties properties = new Properties();
    private static final InputStream patch = ClassLoader.getSystemResourceAsStream("config.properties");

    static {
        try {
            properties.load(patch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return properties.getProperty(key);
    }
}
