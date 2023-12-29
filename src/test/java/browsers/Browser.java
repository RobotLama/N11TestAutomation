package browsers;

public enum Browser {
    CHROME,
    FIREFOX;

    public static String supportedBrowsers() {
        String browsers = "";

        for (Browser browser : Browser.values())
            browsers += browser.name() + " ";
        return browsers;
    }

    public static boolean isSupportedBrowser(String browserName) {
        boolean isSupported = false;

        for (Browser browser : Browser.values()) {
            if (browser.name().equalsIgnoreCase(browserName)) {
                isSupported = true;
                break;
            }
            else isSupported = false;
        }
        return isSupported;
    }
}
