import PageObjectModels.BrowserConfig;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTests {
    private WebDriver driver;
    private BrowserConfig config;

    @BeforeEach
    void launchBrowser() {
        config = new BrowserConfig();
        BrowserConfig config = new BrowserConfig();
        driver = config.BrowserSelect("chrome");
        config.windowMode("portrait");
    }

    //Question formatting tests
    @Test
    public void threeIncorrectOneCorrectAnswer() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        Thread.sleep(200);
        page.answerQuestion(1);
        page.submitAnswer();
        int incorrect = 0, correct = 0;
        for (int i = 1; i <= 4; i++) {
            String ansewerText = page.getAnswer(i).getText();
            System.out.println(ansewerText);
            if (ansewerText.contains("Correct")) {
                correct++;
            } else if (ansewerText.contains("Incorrect")) {
                incorrect++;
            }
        }
        assertEquals(1, correct);
        assertEquals(3, incorrect);
    }

    //Score tests
    @Test
    public void scoreIncrementsCorrectly() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();
        Thread.sleep(200);
        int correctAnswers = 0;
        while (Objects.equals(page.getHeadingText(), "Quiz")) {
            Thread.sleep(100);
            assertTrue(page.getScore().contains(Integer.toString(correctAnswers)));
            Random rand = new Random();
            int nextRandom = rand.nextInt(1, 5);
            page.answerQuestion(nextRandom);
            page.submitAnswer();
            String answerColour = page.getAnswer(nextRandom).getCssValue("background-color");
            if (answerColour.contains("0, 128, 0")) {
                correctAnswers++;
            }
            page.goToNextQuestion();
        }
        //config.takeScreenshot(driver, "QuizTest1.png");
        System.out.println("number correct: " + correctAnswers);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}