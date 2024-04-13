package ProjectIntegration.StepDefinitions.Mobile;

import ProjectIntegration.Managers.AndroidDriverManager;
import ProjectIntegration.Pages.Mobile.LoginPage;
import ProjectIntegration.Pages.Mobile.ProductPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.io.IOException;

public class loginSteps {

    private static AndroidDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;

    public loginSteps() throws IOException {
        try {
            driver = AndroidDriverManager.getDriver();
            loginPage = new LoginPage(driver);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Given("User opens the app")
    public void user_opens_the_app() {
        try{
            driver = AndroidDriverManager.getDriver();
            Thread.sleep(10000);
            Assert.assertTrue(loginPage.validateLoginHeader());
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("User selects the country as {string}")
    public void userSelectsTheCountryAs(String country) {
        try {
            loginPage.selectCountry(country);
            Assert.assertEquals(loginPage.verifySelectedCountry(),country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("User enters the {string}")
    public void userEntersTheName(String name) {
        try {
            loginPage.setName(name);
            Assert.assertEquals(loginPage.verifyNameValue(), name);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("User selects Gender as {string}")
    public void userSelectsGenderAs(String gender) throws Exception {
        try {
            loginPage.selectGender(gender);
            Assert.assertEquals(loginPage.validateSelectedGender(gender), gender);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Then("User click on Let's Shop Button")
    public void userClickOnLetSShopButton() throws Exception {
        try {
            productPage = loginPage.clickOnShop();
            Assert.assertTrue(productPage.validateProductHeader());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}