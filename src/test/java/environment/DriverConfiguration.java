package environment;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface DriverConfiguration {

    RemoteWebDriver setUpConfiguration() throws MalformedURLException;

    static String selectEnvironment(String environment) {
        return "https://www.saucedemo.com/";
    }
}