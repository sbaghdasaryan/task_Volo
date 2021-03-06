import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(id = "rb")
    private WebElement table;

    @FindBy(xpath = "//*[text()='Buy']")
    private List<WebElement> buyElems;

    private int[] currencyIndexArray = {6, 8, 10};

    public int[] getCurrencyIndexArray() {
        return currencyIndexArray;
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(table));
    }

    public void clickBuyButton(int i) {
        wait.until(ExpectedConditions.visibilityOfAllElements(buyElems));
        wait.until(ExpectedConditions.elementToBeClickable(buyElems.get(i))).click();
    }

    public List<Float> getCurrencyValues(int i) {
        List<WebElement> webElements;
        String currencyValue = String.format("tr[id] td:nth-of-type(%s)", (currencyIndexArray[i]));

        webElements = wait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector(currencyValue))));

        List<Float> currencyValues = new ArrayList<>();
        for (WebElement webElement : webElements) {
            if (!webElement.getText().isEmpty()) {
                currencyValues.add(Float.parseFloat(webElement.getText()));
            }
        }
        return currencyValues;
    }
}
