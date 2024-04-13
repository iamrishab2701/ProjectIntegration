package ProjectIntegration.Utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidUtils {

    private AndroidDriver driver;
    private WebDriverWait wait;
    public AndroidUtils(AndroidDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public boolean elementIsDisplayed(WebElement element)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickOnElement(WebElement element) throws Exception {
        if (elementIsDisplayed(element))
        {
            element.click();
        }else
        {
            throw new Exception();
        }
    }

    public void scrollUsingUIAutomator(String text)
    {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
    }

    public void enterText(WebElement element, String text) throws Exception {
        if(elementIsDisplayed(element))
        {
            element.sendKeys(text);
        }else
        {
            throw new Exception();
        }
    }

    public String getText(WebElement element) throws Exception {
        if(element.isDisplayed())
        {
            return element.getText();
        }else
        {
            throw new Exception();
        }
    }

    public boolean elementIsSelected(WebElement element)
    {
        if (elementIsDisplayed(element))
        {
            return element.isSelected();
        }else
        {
            return false;
        }
    }

}