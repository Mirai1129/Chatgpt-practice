package tw.mirai1129;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils() {
    }

    public static Properties loadProperties(String path) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
