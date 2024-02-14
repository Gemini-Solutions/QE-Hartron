package com.qa.gemini.stepdefinitions;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.gemini.locators.LoginLocators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

public class Login {
    public static String randomString = "";
    @Given("Navigate to page {string}")
    public void pageNavigate(String page) {
        try {
            Thread.sleep(15000);
            DriverAction.click(By.xpath(LoginLocators.pageNavigate.replace("input", page)), "Navigate to page " + page, "Successfully navigated to page " + page);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Navigate to page-"+page, "Exception encountered- " + e, STATUS.ERR);
        }
    }
    @Given("^Expand the login via dropdown$")
    public void expandLoginViaDropdown() {
        try{
            Thread.sleep(10);
            DriverAction.click(LoginLocators.loginVia);
        }catch(Exception e){
            GemTestReporter.addTestStep("Expand the login via dropdown","Exception encountered- " + e, STATUS.ERR);
        }
    }
    @And("Select {string} from dropdown")
    public void selectFromDropdown(String option) {
        try{
            Thread.sleep(6000);
            if(DriverAction.isExist(By.xpath(LoginLocators.profile.replace("input",option)))) {
                DriverAction.click(By.xpath(LoginLocators.profile.replace("input", option)), "Select " + option + " from dropdown", "Successfully selected " + option + " from dropdown.");
            }else if(DriverAction.isExist(By.xpath(LoginLocators.sectionOptions.replace("input", option)))){
                DriverAction.click(By.xpath(LoginLocators.sectionOptions.replace("input", option)), "Select " + option + " from dropdown", "Successfully selected " + option + " from dropdown.");
            }else{
                GemTestReporter.addReasonOfFailure("Option not selected from dropdown.");
            }
        }catch(Exception e){
            GemTestReporter.addReasonOfFailure(e+" Exception occured on selecting "+option+" from dropdown.");
        }
    }
    @Then("^Login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String username, String password) {

        try {

            DriverAction.waitUntilElementClickable(LoginLocators.usernameField,120);
            System.out.println(randomString);
            DriverAction.typeText(LoginLocators.usernameField,username,"Username Entered","Username entered successfully");
//            DriverAction.typeText(LoginLocators.usernameField, username);

            DriverAction.typeText(LoginLocators.passwordField,password,"Username Entered","Username entered successfully");
//            DriverAction.typeText(LoginLocators.passwordField, password);
            DriverAction.waitUntilElementClickable(LoginLocators.loginBtn,90);
            DriverAction.click(LoginLocators.loginBtn);
            Thread.sleep(25000);

            //wait while the page loads.
//            if(DriverAction.isExist(LoginLocators.spinner));
//            DriverAction.waitUntilElementDisappear(LoginLocators.spinner,20);

            //verify dashboard is displayed on login
            String url= "https://athena-hartron-dev.geminisolutions.com/athena/admin/dashboard";

            if (url.equals(DriverAction.getCurrentURL())) {
                GemTestReporter.addTestStep("Verify dashboard is displayed", "Successfully displayed the dashboard.", STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            System.out.print("Could not login to Athena.");
        }
    }
}
