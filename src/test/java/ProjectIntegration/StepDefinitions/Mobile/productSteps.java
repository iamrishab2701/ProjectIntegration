package ProjectIntegration.StepDefinitions.Mobile;

import ProjectIntegration.Managers.AndroidDriverManager;
import ProjectIntegration.Pages.Mobile.ProductPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.messages.types.Product;
import org.junit.Assert;

public class productSteps {

    private static AndroidDriver driver;
    private ProductPage productPage;

    public productSteps()
    {
        try
        {
            driver = AndroidDriverManager.getDriver();
            productPage = new ProductPage(driver);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Given("User is on product page")
    public void user_is_on_product_page()
    {
        try
        {
            Assert.assertTrue(productPage.validateProductHeader());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @And("User select product {string} and add to cart")
    public void userSelectProductAndAddToCart(String productName) {
        try
        {
            productPage.addProductToCart(productName);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}