package example.stepDefination;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearchStepDefinitions {

    WebDriver driver;

    @Given("I open the browser")
    public void i_open_the_browser() {
//        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:/Users/mpavanka/IdeaProjects/test/webdriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver.get(url);
    }

    @And("I search for {string}")
    public void i_search_for(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        WebElement results = driver.findElement(By.id("search"));
        assert results.isDisplayed();
        driver.quit(); // close the browser after test
    }
}

