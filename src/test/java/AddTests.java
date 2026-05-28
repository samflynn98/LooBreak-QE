import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class AddTests {
    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void addSingleItemTest() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        page.addItem("test");
        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        String itemName = item.getText();
        assertEquals(itemName, "test");
    }

    @Test
    public void addMultipleItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        int itemNumber = 10;
        page.addMultipleItems(10);
        WebElement currentItem = driver.findElement(By.cssSelector("li:nth-child(" + 10 + ") label"));
        //add actual test here
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}