import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTests {

    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void numberOfIncompleteItemsEqualsStatusBar() {

        TodoPage page = new TodoPage(driver);
        page.navigate();
        page.addItem("test");

        WebElement checkbox = driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']"));

        checkbox.click();


        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("0"));
    }

    @Test
    public void noItemsLeftWhenCompleted() {

        TodoPage page = new TodoPage(driver);
        page.navigate();
        page.addItem("test");

        driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']")

        ).click();

        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("0"));
    }

    @Test
    public void completedItemsNotIncludedInStatus() {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        page.addMultipleItems(2);

        driver.findElement(By.cssSelector("[data-testid='todo-item-toggle']")).click();

        String statusText = driver.findElement(By.cssSelector(".todo-count")).getText();

        assertTrue(statusText.contains("1"));
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
