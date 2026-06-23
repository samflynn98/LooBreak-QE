import org.openqa.selenium.WebDriver;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public Homepage navigate() {
        driver.get("http://localhost:5173/");
        return this;
    }

    public String get_title() {
        String title = driver.getTitle();
        return title;
    }
}