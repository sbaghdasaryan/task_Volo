import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "http://rate.am";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public BasePage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void chooseLanguage(String language) {
        String languageId = String.format("ctl00_LanguageMenuControl2_hpLang%s", getLanguage(language));

        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.id(languageId)))).click();

    }

    public int getLanguage(String string) {
        int n = 1;
        switch(string)
        {
            case "en":
                n = 1;
                break;
            case "am":
                n = 2;
                break;
            case "ru":
                n = 3;
                break;
        }
        return n;
    }
}
