package config;

import browsers.Browser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static browsers.Browser.isSupportedBrowser;
import static browsers.Browser.supportedBrowsers;

public class Config {

    protected static final Properties properties;
    public static final Browser BROWSER;
    public static final String BASE_URL;

    static {
        properties = getProperties();
        BROWSER = Browser.valueOf(getBrowser());
        BASE_URL = properties.getProperty("n11.prod");
    }

    private static Properties getProperties() {
        String configFile = "properties/config.properties";
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(configFile);
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    private static String getBrowser() {
        String browser = properties.getProperty("browser");
        if (isSupportedBrowser(browser))
            return browser;
        else throw new IllegalArgumentException(browser + " is not supported or the browser name was written incorrect!\n"
                + "Supported browsers: " + supportedBrowsers());
    }
}
