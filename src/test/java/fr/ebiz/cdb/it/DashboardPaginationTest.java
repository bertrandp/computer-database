package fr.ebiz.cdb.it;

import fr.ebiz.cdb.it.utils.ITHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DashboardPaginationTest {
    public static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    public static final String SELENIUM_PROPERTIES = "selenium.properties";
    public static final String DAO_PROPERTIES = "dao.properties";
    public static final String BASE_URL = "base.url";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private WebDriver driver;
    private String baseUrl;
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
    public void testDashboardPagination() throws Exception {
        driver.get(baseUrl + "/dashboard");

        checkComputerTableSize(50);

        driver.findElement(By.linkText("5")).click();

//        driver.findElement(By.xpath("(//a[contains(text(),'10')])[5]")).click();
//        driver.findElement(By.linkText("6")).click();
//        driver.findElement(By.cssSelector("a > span")).click();

        //checkComputerTableSize(10);
    }

    private void checkComputerTableSize(int size) {
        List computerList = driver.findElements(By.tagName("tr"));
        assertThat(computerList.size(), is(size + 1));
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
