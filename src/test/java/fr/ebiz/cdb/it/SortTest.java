package fr.ebiz.cdb.it;

import java.util.Properties;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import fr.ebiz.cdb.it.utils.ITHelper;
import org.junit.*;

import static fr.ebiz.cdb.it.DashboardPaginationTest.BASE_URL;
import static fr.ebiz.cdb.it.DashboardPaginationTest.SELENIUM_PROPERTIES;
import static fr.ebiz.cdb.it.DashboardPaginationTest.WEBDRIVER_GECKO_DRIVER;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SortTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    Properties seleniumProperties = ITHelper.readPropertiesFile(SELENIUM_PROPERTIES);
    System.setProperty(WEBDRIVER_GECKO_DRIVER, seleniumProperties.getProperty(WEBDRIVER_GECKO_DRIVER));
    driver = new FirefoxDriver();
    baseUrl = seleniumProperties.getProperty(BASE_URL);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSort() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("apple");
    driver.findElement(By.id("searchsubmit")).click();
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
