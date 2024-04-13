package ProjectIntegration.StepDefinitions;

import ProjectIntegration.Managers.AndroidDriverManager;
import ProjectIntegration.StepDefinitions.Mobile.loginSteps;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    private AndroidDriver driver;
    private loginSteps loginSteps;

    public Hooks() throws IOException {
        driver = AndroidDriverManager.getDriver();
        loginSteps = new loginSteps();
    }

    @After
    public void teardown() throws IOException {
        AndroidDriverManager.quitDriver();
    }

    @Before("@PurchaseProduct")
    public void productPurchaseBeforeStep() throws Exception {
        loginSteps.user_opens_the_app();
        loginSteps.userSelectsTheCountryAs("Afghanistan");
        loginSteps.userEntersTheName("Rishab");
        loginSteps.userSelectsGenderAs("Male");
        loginSteps.userClickOnLetSShopButton();
    }
}
