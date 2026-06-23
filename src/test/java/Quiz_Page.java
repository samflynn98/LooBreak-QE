import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Quiz_Page {
    protected WebDriver driver;

    public Quiz_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Quiz_Page navigate() {
        driver.get("URL");
        return this;
    }

    public void example(String text) {
        WebElement element = driver.findElement(By.id("example"));
        element.sendKeys(text);
    }
}