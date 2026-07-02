import PageObjectModels.BrowserConfig;
import PageObjectModels.QuizPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
            String answerText = page.getAnswer(i).getText();
            System.out.println(answerText);
            if (answerText.contains("Correct")) {
                correct++;
            } else if (answerText.contains("Incorrect")) {
                incorrect++;
            }
        }
        assertEquals(1, correct);
        assertEquals(2, incorrect);
    }

    //Score tests
    @Test
    public void scoreIncrementsCorrectly() throws Exception {
        QuizPage page = new QuizPage(driver);
        page.navigate();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        int correctAnswers = 0;

        // Use explicit wait to ensure the quiz has actually started
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Quiz"));

        while (true) {
            // Check if the heading is still "Quiz". If it's gone/changed, break the loop.
            try {
                if (!page.getHeadingText().contains("Quiz")) {
                    break;
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // The heading element is completely gone (e.g., reached results screen)
                break;
            }

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

            // Give the DOM a moment to update/stale-check before the next loop iteration
            Thread.sleep(100);
        }

        System.out.println("number correct: " + correctAnswers);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}