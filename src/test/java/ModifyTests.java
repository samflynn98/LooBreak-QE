// in MakersSearchTest.java

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.*;

public class ModifyTests {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    public void modifyItem() throws Exception {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys("test");
        searchBar.sendKeys(Keys.ENTER);
        WebElement item = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        new Actions(driver)
                .doubleClick(item)
                .perform();
        WebElement updatedItem = driver.findElement(By.cssSelector(".input-container:nth-child(1) > #todo-input"));
        updatedItem.sendKeys("123");
        updatedItem.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        String itemName = item.getText();
        assertEquals(itemName, "test123");
        Thread.sleep(5000);
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
