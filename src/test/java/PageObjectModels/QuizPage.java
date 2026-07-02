package PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class QuizPage {
    protected WebDriver driver;

    public QuizPage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate
    public QuizPage navigate() {
        driver.get("http://localhost:5173/quiz");
        return this;
    }

    //Heading and Title
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String getHeadingText() {
        WebElement heading = driver.findElement(By.xpath("//h1[text()='Quiz']"));
        return heading.getText();
    }

    //Question Answering
    public void answerQuestion(int id) {
        driver.findElement(By.cssSelector("button[data-slot='button']:nth-of-type(" + id + ")")).click();
    }

    public void submitAnswer() {
        driver.findElement(By.xpath("//button[normalize-space(translate(., '\u00A0', ' '))='Submit']")).click();
    }

    public void goToNextQuestion() {
        driver.findElement(By.cssSelector("button[data-testid='arrow-button']")).click();
    }

    public void answerAllQuestions() {
        QuizPage page = new QuizPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1. Check if the element is present in the DOM first
        while (!driver.findElements(By.xpath("//h1[text()='Quiz']")).isEmpty()) {

            // 2. Select the first option
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-slot='button']:nth-of-type(1)"))).click();

            // 3. Click Submit
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(translate(., '\u00A0', ' '))='Submit']"))).click();

            // 4. Click the Next Arrow
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='arrow-button']"))).click();

            // Short wait to allow the next question DOM transition to begin
            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    //Question attributes
    public String getQuestionNumber() {
        String QuestionNum = driver.findElement(By.cssSelector("[data-slot='item-title']")).getText();
        return QuestionNum;
    }

    public String getScore() {
        String score = driver.findElement(By.className("text-accent")).getText();
        return score;
    }

    public WebElement getAnswer(int id) {
        WebElement answer = driver.findElement(By.cssSelector(".justify-center:nth-child(" + id + ")"));
        return answer;
    }

    //Results Sub Screen
    public void generatePlayername() {
        driver.findElement(By.cssSelector("button[data-testid='generate-button']")).click();
    }

    public void enterPlayername(String name) {
        driver.findElement(By.id("playername")).sendKeys(name);
    }

    public String getPlayername() {
        WebElement playerNameInput = driver.findElement(By.name("playername"));
        String playername = playerNameInput.getAttribute("value");
        return playername;
    }

    public String getFinalScore() {
        String finalScore = driver.findElement(By.cssSelector("[data-testid='score']")).getText();
        return finalScore;
    }

    public void submitPlayername() {
        driver.findElement(By.cssSelector("button[data-testid='submit-button']")).click();
    }

    public String getWarningMessage () {
        String warning = driver.findElement(By.cssSelector("[data-testid='playername-error']")).getText();
        return warning;
    }
}