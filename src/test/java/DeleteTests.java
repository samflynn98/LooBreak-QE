// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTests {
    private ChromeDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void deleteSingleItemTest() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        page.addItem("test");
        int itemID = 1;
        page.deleteItem(itemID);
        boolean isPresent = driver.findElements(By.cssSelector("li:nth-child(" + itemID + ") label")).size() > 0;
        assertFalse(isPresent);
    }

    @Test
    public void deleteMultipleItems() throws Exception {
        TodoPage page = new TodoPage(driver);
        page.navigate();
        int itemNumber = 10;
        page.addMultipleItems(itemNumber);
        while (itemNumber > 1) {
            WebElement itemToDelete = driver.findElement(By.cssSelector("li:nth-child(" + (itemNumber - 1) + ") label"));
            new Actions(driver)
                    .moveToElement(itemToDelete)
                    .perform();
            Thread.sleep(100);
            driver.findElement(By.cssSelector("li:nth-child(" + (itemNumber - 1) + ") .destroy")).click();
            boolean isPresent = driver.findElements(By.cssSelector("li:nth-child(" + itemNumber + ") label")).size() > 0;
            assertFalse(isPresent);
            itemNumber--;
        }
    }

    @Test
    public void noItemsNothingToDelete() throws Exception {

        TodoPage page = new TodoPage(driver);
        page.navigate();

        int itemCount = driver.findElements(
                By.cssSelector("[data-testid='todo-item-label']")
        ).size();

        assertEquals(0, itemCount);

        int clearCompletedButtons = driver.findElements(
                By.cssSelector("[data-testid='footer-clear-completed']")
        ).size();

        assertEquals(0, clearCompletedButtons);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}