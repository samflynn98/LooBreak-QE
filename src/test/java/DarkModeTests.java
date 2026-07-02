import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import PageObjectModels.Navbar;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;


import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DarkModeTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    @Test
    public void darkAndLightBackgroundsCorrect() throws Exception {
        Homepage page = new Homepage(driver);
        page.navigate();
        Navbar navbar = new Navbar(driver);
        //config.takeScreenshot(driver, "SafariDarkModeTest1.png");
        Thread.sleep(100);
        navbar.toggleDarkMode();
        //config.takeScreenshot(driver, "SafariDarkModeTest2.png");
        Thread.sleep(100);
        WebElement body = driver.findElement(By.tagName("body"));
        String darkColour = body.getCssValue("background-color");
        assertTrue(darkColour.contains("15, 21, 21"));
        Thread.sleep(100);
        navbar.toggleDarkMode();
        String lightColour = body.getCssValue("background-color");
        assertTrue(lightColour.contains("253, 253, 252"));
        Thread.sleep(100);
        //config.takeScreenshot(driver, "SafariDarkModeTest3.png");
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
    
}