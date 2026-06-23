import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public Homepage navigate() {
        driver.get("URL");
        return this;
    }

    public void example(String text) {
        WebElement element = driver.findElement(By.id("example"));
        element.sendKeys(text);
    }
}