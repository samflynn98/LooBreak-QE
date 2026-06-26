import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Leaderboard_Page {
    protected WebDriver driver;

    public Leaderboard_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Leaderboard_Page navigate() {
        driver.get("http://localhost:5173/leaderboard");
        return this;
    }

    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h2"));
        return heading.getText();
    }
}