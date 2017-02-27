package fr.ebiz.cdb.it.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by bpestre on 24/02/17.
 */
public class FormAddComputerTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/fr/ebiz/cdb/selenium/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testFormAddComputer() throws Exception {
        driver.get(baseUrl + "dashboard");
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
}
