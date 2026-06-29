package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navbar {
    protected WebDriver driver;

    public Navbar(WebDriver driver) {
        this.driver = driver;
    }

    public void goToNavbarPage(String page) {
        driver.findElement(By.linkText(page)).click();
    }

    public void toggleDarkMode() {
        driver.findElement(By.cssSelector("button:nth-child(5)")).click();
    }
}