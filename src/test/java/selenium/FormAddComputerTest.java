package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by bpestre on 24/02/17.
 */
public class FormAddComputerTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/selenium/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testFormAddComputer() throws Exception {
        driver.get(baseUrl + "/dashboard");
        driver.findElement(By.id("addComputer")).click();
        driver.findElement(By.id("computerName")).clear();
        driver.findElement(By.id("computerName")).sendKeys("test");
        checkNameIsValidate();

        driver.findElement(By.id("introduced")).clear();
        driver.findElement(By.id("introduced")).sendKeys("test");
        checkDateIsInvalid();
        
        driver.findElement(By.id("introduced")).clear();
        driver.findElement(By.id("introduced")).sendKeys("");
        driver.findElement(By.id("discontinued")).clear();
        driver.findElement(By.id("discontinued")).sendKeys("11/11/1111");
        driver.findElement(By.id("introduced")).clear();
        driver.findElement(By.id("introduced")).sendKeys("12/12/1212");

        checkDateIsInvalid();
    }

    private void checkNameIsValidate() {
        WebElement formName = driver.findElement(By.id("form-name"));
        assertThat(formName.getAttribute("class"), is("form-group has-feedback has-success"));
    }

    private void checkDateIsInvalid() {
        WebElement formIntroduced = driver.findElement(By.id("form-introduced"));
        assertThat(formIntroduced.getAttribute("class"), is("form-group has-feedback has-error"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
