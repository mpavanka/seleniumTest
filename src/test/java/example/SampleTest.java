package example;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class SampleTest {

    WebDriver driver;
    String searchValue;

    @BeforeClass
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/mpavanka/IdeaProjects/test/webdriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Parameters("searchValue")
    @Description("opening google and performing search in google")
    public void openGoogle(String searchValue) {
        this.searchValue = searchValue;
        // Open Google in the first tab
        driver.get("https://www.google.com");

        // Find the search bar and enter "Google CEO"
        WebElement searchTerm = driver.findElement(By.name("q"));
        searchTerm.sendKeys(searchValue);
        searchTerm.sendKeys(Keys.ENTER);

        // Print current time before execution
        LocalTime timeBeforeExecution = LocalTime.now();
        System.out.println(timeBeforeExecution + " - Time before execution");

        // Find the link for "Sundar Pichai" in the search results
        WebElement getCeo = driver.findElement(By.linkText("Sundar Pichai"));
        String name = getCeo.getText();
        System.out.println("CEO: " + name);

        // Print current time after execution
        LocalTime timeAfterExecution = LocalTime.now();
        System.out.println(timeAfterExecution + " - Time after execution");

        // Open a new tab
        ((ChromeDriver) driver).executeScript("window.open()");
    }

//    @Test(dependsOnMethods = "openGoogle")
    @Test(enabled = false)
    @Parameters("searchValue")
    public void openYahoo(String searchValue) {
        this.searchValue = searchValue;
        // Get all open window handles (tabs)
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the newly opened second tab
        driver.switchTo().window(tabs.get(1));

        // Open Yahoo in the second tab
        driver.get("https://www.yahoo.com");

        // Find the search bar and enter "Google CEO"
        WebElement searchTerm = driver.findElement(By.id("ybar-sbq"));
        searchTerm.sendKeys(searchValue);
        searchTerm.sendKeys(Keys.ENTER);

        LocalTime timeBeforeExecution = LocalTime.now();
        System.out.println(timeBeforeExecution + " - Time before execution");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ceoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Sundar Pichai')]")));
        System.out.println("CEO: " + ceoElement.getText());

        LocalTime timeAfterExecution = LocalTime.now();
        System.out.println(timeAfterExecution + " - Time after execution");

        // Optionally, switch back to the first tab
        driver.switchTo().window(tabs.get(0));
    }
}
