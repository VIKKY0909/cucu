package environment;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;

public class WebRemote implements DriverConfiguration {
    @Override
    public RemoteWebDriver setUpConfiguration() throws MalformedURLException {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/test/drivers/chromedriverLatest/");
        File app = new File(appDir, "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        System.setProperty("webdriver.chrome.driver", app.getAbsolutePath());
        RemoteWebDriver driver = new ChromeDriver(options);
        if (System.getProperty("setBrowser").equals("WebBrowser")) {
            driver.manage().window().maximize();
        } else if (System.getProperty("setBrowser").equals("Mobile")) {
            Dimension dimension = new Dimension(414, 896);
            driver.manage().window().setSize(dimension);
        }
        driver.get(DriverConfiguration.selectEnvironment(System.getProperty("setEnvironment")));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }
}