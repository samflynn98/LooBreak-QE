package PageObjectModels;

import org.openqa.selenium.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement heading = driver.findElement(By.xpath("//h1[@data-testid='homepage-header']"));
        return heading.getText();
    }

    // Icebreakers
    public By icebreakerButton = By.cssSelector("[data-testid='icebreaker-reveal-btn']");
    public By icebreakerList = By.cssSelector("[data-testid='icebreaker-list']");
    public By icebreakerCloseButton = By.cssSelector("[data-slot='drawer-close']");

    public boolean isIcebreakerVisible() {
        for (WebElement element : driver.findElements(icebreakerList)) {
            if (element.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    public void showIcebreaker() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        if (!isIcebreakerVisible()) {
            wait.until(ExpectedConditions.elementToBeClickable(icebreakerButton)).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(icebreakerList));
    }

    public void hideIcebreaker() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        if (!driver.findElements(icebreakerCloseButton).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(icebreakerCloseButton)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(icebreakerList));
        }
    }

    public String getIcebreakerText() {
        return driver.findElement(icebreakerList).getText();
    }

    public String getLootipText() {
        String lootip = driver.findElement(By.className("_tipText_1iam4_24")).getText();
        return lootip;
    }
}