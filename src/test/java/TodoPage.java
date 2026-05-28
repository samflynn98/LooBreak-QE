import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoPage {
    protected WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/react/dist/#/active");
    }

    public void addItem(String text) {
        WebElement searchBar = driver.findElement(By.id("todo-input"));
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
    }
}