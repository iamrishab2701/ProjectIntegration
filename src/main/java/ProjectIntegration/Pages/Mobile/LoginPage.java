package ProjectIntegration.Pages.Mobile;

import ProjectIntegration.Utils.AndroidUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AndroidDriver driver;
    private AndroidUtils androidUtils;

    private ProductPage productPage;

    public LoginPage(AndroidDriver driver)
    {
        this.driver = driver;
        this.androidUtils = new AndroidUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement loginHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement countryDropdown;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"India\"]")
    WebElement countryIndia;

    @AndroidBy(id = "com.androidsample.generalstore:id/nameField")
    WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    WebElement maleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    WebElement femaleRadio;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letsShopButton;



    public boolean validateLoginHeader()
    {
        return androidUtils.elementIsDisplayed(loginHeader);
    }

    public void clickCountryDropdown() throws Exception {
        androidUtils.clickOnElement(countryDropdown);
    }

    public void setName(String name) throws Exception {
        androidUtils.enterText(nameField, name);
    }

    public String verifyNameValue() throws Exception {
        return androidUtils.getText(nameField);
    }

    public void clickOnCountry(String country) throws Exception {
        androidUtils.clickOnElement(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+country+"\"]")));
    }

    public void selectCountry(String country) throws Exception {
        clickCountryDropdown();
        androidUtils.scrollUsingUIAutomator(country);
        clickOnCountry(country);
    }

    public String verifySelectedCountry() throws Exception {
        return androidUtils.getText(countryDropdown);
    }

    public void selectGender(String gender) throws Exception {
        if(gender.equalsIgnoreCase("Male"))
        {
            androidUtils.clickOnElement(maleRadio);
        } else if (gender.equalsIgnoreCase("Female")) {
            androidUtils.clickOnElement(femaleRadio);
        }
    }

    public String validateSelectedGender(String gender) throws Exception {
        if(gender.equalsIgnoreCase("Male"))
        {
            return androidUtils.getText(maleRadio);
        }else if(gender.equalsIgnoreCase("Female"))
        {
            return androidUtils.getText(femaleRadio);
        }
        return "Incorrect Value selected";
    }

    public ProductPage clickOnShop() throws Exception {
        androidUtils.clickOnElement(letsShopButton);
        return new ProductPage(driver);
    }

}
