package ProjectIntegration.Pages.Mobile;

import ProjectIntegration.Utils.AndroidUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    private AndroidDriver driver;
    private AndroidUtils androidUtils;

    public ProductPage(AndroidDriver driver)
    {
        this.driver = driver;
        this.androidUtils = new AndroidUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id ="com.androidsample.generalstore:id/toolbar_title")
    WebElement productHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")
    WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\"]")
    List<WebElement> products;

    public boolean validateProductHeader()
    {
        return androidUtils.elementIsDisplayed(productHeader);
    }

    // Add to Cart xpath - //android.widget.TextView[@resource-id="com.androidsample.generalstore:id/productAddCart"]
    // Product xpath - //android.widget.TextView[@resource-id="com.androidsample.generalstore:id/productName"]

    public void addProductToCart(String productName) throws Exception {
        androidUtils.scrollUsingUIAutomator(productName);
        for(WebElement product: products)
        {
            if(product.getText().equalsIgnoreCase(productName))
            {
                androidUtils.clickOnElement(addToCartButton);
            }
        }
    }
}
