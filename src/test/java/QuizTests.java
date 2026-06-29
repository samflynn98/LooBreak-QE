import PageObjectModels.BrowserConfig;
import PageObjectModels.Homepage;
import PageObjectModels.Navbar;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTests {
    private WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("maximize");
    }

    @Test
    public void scoreIncrementsCorrectly() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        int correctAnswers = 0;
        for (int i = 1; i < 20; i++) {
            assertTrue(page.getScore().contains(Integer.toString(correctAnswers)));
            Random rand = new Random();
            int nextRandom = rand.nextInt(1, 5);
            page.answerQuestion(nextRandom);
            page.submitAnswer();
            String answerColour = page.getAnswer(nextRandom).getCssValue("background-color");
            if (answerColour.equals("rgba(0, 128, 0, 1)")) {
                correctAnswers++;
            }
        }
        System.out.println("number correct: " + correctAnswers);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}