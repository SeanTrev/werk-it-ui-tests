package com.xyzcorp;
import java.time.Duration;

import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;




public class werkitSeleniumTests {

    WebDriver driver;

    @BeforeAll 
    void setupAll(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://staging.tiered-planet.net/werk-it");
        Dimension windowDim = driver.manage().window().getSize();
        if (windowDim.getWidth() <= 992){
            WebElement hamburger = driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/nav/div/button"));
            hamburger.click();
        }

    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        driver.quit();
    }






    @Test
    void testRelativeLocators() throws IOException {
        List<WebElement> sections = driver.findElements(By.tagName("section"));
        
    }
    @Test
    void testHomeButton() {
        Actions actions = new Actions(driver);
        WebElement homeBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[1]/a[1]"));
        actions.moveToElement(homeBtn).build().perform();
        actions.pause(3);
        homeBtn.click();
        String siteURL = driver.getCurrentUrl();
        assertThat(siteURL).isEqualTo("https://staging.tiered-planet.net/");
    }
    @Test
    void testLoginButton (){
        Actions actions = new Actions(driver);
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[1]/a[2]"));
        actions.moveToElement(loginBtn).build().perform();
        actions.pause(3);
        loginBtn.click();
        String siteURL = driver.getCurrentUrl();
        assertThat(siteURL).isEqualTo("https://staging.tiered-planet.net/login");
    }
    @Test
    void testExercisesButton() {
        Actions actions = new Actions(driver);
        WebElement exercisesBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[1]/a[3]"));
        actions.moveToElement(exercisesBtn).build().perform();
        actions.pause(3);
        exercisesBtn.click();
        String siteURL = driver.getCurrentUrl();
        assertThat(siteURL).isEqualTo("https://staging.tiered-planet.net/exercises");
    }
    @Test
    void testNutritionButton() {
        Actions actions = new Actions(driver);
        WebElement nutritionBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[1]/a[4]"));
        actions.moveToElement(nutritionBtn).build().perform();
        actions.pause(3);
        nutritionBtn.click();
        String siteURL = driver.getCurrentUrl();
        assertThat(siteURL).isEqualTo("https://staging.tiered-planet.net/nutrition");
    }
    @Test
    void testCreateProfileButton() {
        Actions actions = new Actions(driver);
        WebElement registerBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[2]/a[1]"));
        actions.moveToElement(registerBtn).build().perform();
        actions.pause(3);
        registerBtn.click();
        String siteURL = driver.getCurrentUrl();
        assertThat(siteURL).isEqualTo("https://staging.tiered-planet.net/register");
    }

    @Test
    void testEmialIsValid (){
        testCreateProfileButton();
        WebElement emailTextField = driver.findElement(By.xpath("//*[@id='register']/div[3]/label/input"));
        String emailValue = "TestUser111@g.com";
        emailTextField.sendKeys(emailValue);


        assertThat(emailTextField.getAttribute("value")).contains("@");
    }
   

    @Test
    void testCreateProfileAndLogin (){
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        WebElement registerBtn = driver.findElement(By.xpath("//*[@id='navbarScroll']/div[2]/a[1]"));
        registerBtn.click();

        WebElement nameTextField = driver.findElement(By.xpath("//*[@id='register']/div[1]/label/input"));
        String nameValue = "Test";
        nameTextField.sendKeys(nameValue);
        assertThat(nameTextField.getAttribute("value")).isEqualTo(nameValue);

        WebElement lastNameTextField = driver.findElement(By.xpath("//*[@id='register']/div[2]/label/input"));
        String lastNameValue = "User";
        lastNameTextField.sendKeys(lastNameValue);
        assertThat(lastNameTextField.getAttribute("value")).isEqualTo(lastNameValue);

        WebElement emailTextField = driver.findElement(By.xpath("//*[@id='register']/div[3]/label/input"));
        String emailValue = "TestUser111@g.com";
        emailTextField.sendKeys(emailValue);
        assertThat(emailTextField.getAttribute("value")).isEqualTo(emailValue);

        WebElement usernameField = driver.findElement(By.xpath("//*[@id='register']/div[4]/label/input"));
        String usernameValue = "TestUser111";
        usernameField.sendKeys(usernameValue);
        assertThat(usernameField.getAttribute("value")).isEqualTo(usernameValue);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id='register']/div[5]/label/input"));
        String passwordValue = "password";
        passwordField.sendKeys(passwordValue);
        assertThat(passwordField.getAttribute("value")).isEqualTo(passwordValue);

        WebElement submitCredentialsBtn = driver.findElement(By.xpath("//*[@id='register']/input"));
        submitCredentialsBtn.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertThat(alert.getText()).contains("You logged in with TestUser111");
        alert.accept();
    }

    @Test
    void testLogin () {
        testHomeButton();
        WebElement usernameField = driver.findElement(By.xpath("//*[@id='username']"));
        String usernameValue = "TestUser111";
        usernameField.sendKeys(usernameValue);
        assertThat(usernameField.getAttribute("value")).isEqualTo(usernameValue);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id='password']"));
        String passwordValue = "password";
        passwordField.sendKeys(passwordValue);
        assertThat(passwordField.getAttribute("value")).isEqualTo(passwordValue);

        WebElement submitCredentialsBtn = driver.findElement(By.xpath("//*[@id='login']/input"));
        submitCredentialsBtn.click();

    }


    //Things to mention
    //Must close hamburgers menu after selecting from it
    //Must have min width and min height of 150x700 with hamburger closed too be able to click all buttons without scrolling
    @Test
    void executeTestsWithSmallWindow() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(400, 800));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Dimension windowDim = driver.manage().window().getSize();
        WebElement hamburger = driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/nav/div/button"));
        
        hamburger.click();
        testHomeButton();
        hamburger.click();

        hamburger.click();
        testNutritionButton();
        hamburger.click();

        hamburger.click();
        testExercisesButton();
        hamburger.click();

        hamburger.click();
        testCreateProfileAndLogin();
        hamburger.click();
    }
    
    @Test
    void testNoCookiesWithoutAccount () {
        Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(0);
    }
    @Test
    void testNoCookiesWithAccount () {
        testLogin();
        Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        Cookie newCookie = new Cookie("id", "26");
        options.addCookie(newCookie);
        cookies = options.getCookies();
        Cookie id = options.getCookieNamed("id");
        System.out.println("----------------------------------------------------------------");
        System.out.println(id);
        
        assertThat(cookies).hasSize(1);

    }
    


}
