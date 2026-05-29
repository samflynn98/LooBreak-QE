import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTests {
    private WebDriver driver;
    String browser = "firefox";

    @BeforeEach
    void launchBrowser() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
    }

    @Test
    public void deleteSingleItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        int numberOfItemsToAdd = 1;
        page.addMultipleItems(numberOfItemsToAdd);
        int numberOfItemsToDelete = 1;
        page.deleteMultipleItems(numberOfItemsToDelete);
        assertEquals((numberOfItemsToAdd-numberOfItemsToDelete), page.getNumberOfItems());
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
    public void deleteAllItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        int numberOfItemsToAdd = 10;
        page.addMultipleItems(numberOfItemsToAdd);
        int numberOfItemsToDelete = 10;
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