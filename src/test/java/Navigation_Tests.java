import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Navigation_Tests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        Browser_Config config = new Browser_Config();
        driver = config.BrowserSelect("chrome");
        config.windowMode("maximize");
    }

    @Test
    public void canNavigateToQuizPageFromHomepage() throws Exception {
        Homepage page = new Homepage(driver).navigate();
        page.navigateToPage("quiz").click();
        assertEquals("http://localhost:5173/quiz", driver.getCurrentUrl());
    }

    @Test
    public void canNavigateToNextQuizQuestion() throws Exception {
        Quiz_Page page = new Quiz_Page(driver).navigate();
        Thread.sleep(2000);
        page.goToNextQuestion();
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}