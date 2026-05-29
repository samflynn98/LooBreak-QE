import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTests {
    private WebDriver driver;
    String browser = "safari";

    @BeforeEach
    void launchBrowser() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }
    }

    @Test
    public void deleteMultipleItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        int numberOfItemsToAdd = 10;
        page.addMultipleItems(numberOfItemsToAdd);
        int numberOfItemsToDelete = 5;
        page.deleteMultipleItems(numberOfItemsToDelete);
        assertEquals((numberOfItemsToAdd-numberOfItemsToDelete), page.getNumberOfItems());
    }

    @Test
    public void noItemsNothingToDeleteTest() throws Exception {
        int itemCount = driver.findElements(By.cssSelector("[data-testid='todo-item-label']")).size();
        assertEquals(0, itemCount);
        int clearCompletedButtons = driver.findElements(By.cssSelector("[data-testid='footer-clear-completed']")).size();
        assertEquals(0, clearCompletedButtons);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}