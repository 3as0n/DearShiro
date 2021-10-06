package core.util;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.util.Properties;

public class ConfigPropertiesReader {
    private static final String filePath = "src/main/resources/config.properties";

    public static String getProp(String key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(filePath));
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
