import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestHome {
    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuiteMethod() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClassMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        softAssert = new SoftAssert();
    }

    @Test
    public void testSorting() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.chooseLanguage("en");
        homePage.waitForPageLoad();

        int currenciesCount = homePage.getCurrencyIndexArray().length;
        for (int i = 0; i < currenciesCount; i++) {
            homePage.clickBuyButton(i);

            List<Float> currencyValues = homePage.getCurrencyValues(i);
            for (int j = 0; j < currencyValues.size() - 1; j++) {
                softAssert.assertTrue(currencyValues.get(j + 1) <= currencyValues.get(j));
            }
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
