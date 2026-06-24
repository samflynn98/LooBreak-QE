import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Browser_Config {
    protected WebDriver driver;

    public WebDriver BrowserSelect(String browser) {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }
        return driver;
    }

    public void windowMode(String mode) {
        if (mode.equals("maximize")) {
            driver.manage().window().maximize();
        }
        else if (mode.equals("portrait")) {
            driver.manage().window().setSize(new Dimension(720, 1080));
        }
    }
}