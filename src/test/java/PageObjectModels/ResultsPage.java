package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {
    protected WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultsPage navigate() {
        driver.get("http://localhost:5173/results");
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