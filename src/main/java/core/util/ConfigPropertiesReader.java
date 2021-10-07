package core.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.Properties;

public class ConfigPropertiesReader {

    public static String getProp(String key) {
        try {
            InputStream patch = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(patch);
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
