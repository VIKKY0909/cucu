package environment;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class WebLocal implements DriverConfiguration {

    @Override
    public RemoteWebDriver setUpConfiguration() {
        try {
            WebDriverManager.chromedriver().setup(); // Setup WebDriverManager
            WebDriver driver = new ChromeDriver();
            
            String browserType = System.getProperty("setBrowser", "WebBrowser");
            
            if (browserType.equals("WebBrowser")) {
                driver.manage().window().maximize();
            } else if (browserType.equals("Mobile")) {
                Dimension dimension = new Dimension(414, 896);
                driver.manage().window().setSize(dimension);
            }
            
            String environmentUrl = DriverConfiguration.selectEnvironment(System.getProperty("setEnvironment"));
            System.out.println("Navigating to: " + environmentUrl);
            driver.get(environmentUrl);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            return (RemoteWebDriver) driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }
    }
}
