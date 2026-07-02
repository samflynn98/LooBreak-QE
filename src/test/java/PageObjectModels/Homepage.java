package PageObjectModels;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class Homepage {
    protected WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigation
    public Homepage navigate() {
        driver.get("http://localhost:5173/");
        return this;
    }

    public WebElement navigateToPage(String targetPath) {
        WebElement newPage = driver.findElement(By.cssSelector("a[href*='" + targetPath + "']"));
        return newPage;
    }

    //Title & Heading
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.tagName("h1"));
        return heading.getText();
    }

    //Icebreakers
    public void showIcebreaker() {
        driver.findElement(By.cssSelector(".hover\\3A bg-accent")).click();
    }

    public void hideIcebreaker() {
        driver.findElement(By.cssSelector("[data-slot=drawer-close]")).getText();
    }

    public String getIcebreakerText() {
        String icebreaker = driver.findElement(By.className("space-y-4")).getText();
        return icebreaker;
    }

    public String getLootipText() {
        String lootip = driver.findElement(By.className("_tipText_oj9jv_24")).getText();
        return lootip;
    }
}