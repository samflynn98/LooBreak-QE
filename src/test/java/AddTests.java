import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import static org.junit.jupiter.api.Assertions.*;

public class AddTests {
    private WebDriver driver;
    String browser = "chrome";

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
    public void addSingleItemTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("test");
        assertEquals("test", page.getItemText(1));
    }

    @Test
    public void addNumbersTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("0123456789");
        assertEquals("0123456789", page.getItemText(1));
    }

    @Test //this test is failing due to some symbols like "&", "/" etc not inputting properly (known bug)
    public void addSpecialCharactersTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("~`!@#%^&*()_-+={[}]|:;\"'<,>.?/$€£¥¢©®™§¶±÷×≠≤≥∞π°");
        assertEquals("~`!@#%^&*()_-+={[}]|:;\"'<,>.?/$€£¥¢©®™§¶±÷×≠≤≥∞π°", page.getItemText(1));
    }

    @Test //does not run in Chrome as doesn't support these characters
    public void addEmojisTest() throws Exception {
        if (browser.equals("firefox") || browser.equals("safari")) {
            TodoPage page = new TodoPage(driver).navigate();
            String emojis = "😀😎🔥🚀💡🎉💯❤️🌟✨🎨🍕🌍🚀🤖💻🎧📱🚗🐾🛠️🔮🛍️🎈🏆";
            page.addItem(emojis);
            Thread.sleep(2000);
            assertEquals(emojis, page.getItemText(1));
        }
        else System.out.println("Test not supported in Chrome");
    }

    @Test
    public void cannotAddEmptyValueTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("");
        assertEquals(0, page.getNumberOfItems());
    }

    @Test
    public void cannotAddSingleCharacterTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        page.addItem("a");
        assertEquals(0, page.getNumberOfItems());
    }

    @Test
    public void addMultipleItemsTest() throws Exception {
        TodoPage page = new TodoPage(driver).navigate();
        int numberOfItems = 10;
        page.addMultipleItems(numberOfItems);
        assertEquals(numberOfItems, page.getNumberOfItems());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}