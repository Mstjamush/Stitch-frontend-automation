package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import general.EnvGlobals;
import general.GeneralFunctions;
import general.MainCall;
import general.SeleniumFunctions;
import org.junit.Assert;


import static config.ConfigProperties.dashboardUrl;
import static general.EnvGlobals.lastName;

public class LoginSteps {

    @Given("^User is arrive to Landing Page$")
    public void user_is_arrive_to_Landing_Page() {
        MainCall.landingPage.arriveToLandingPage();
    }
    @Given("^I launch Application$")
    public void createTodo() {
        MainCall.landingPage.arriveToLandingPage();
        MainCall.landingPage.enterToDo("test");
        MainCall.landingPage.clickSubmit();
    }
    @When("^I want to Close Driver$")
    public void i_want_to_Close_Driver() throws Exception {
        Thread.sleep(3000);
        MainCall.webDriverFactory.finishDriver();
    }
    @Given("^I am Testing Case : \"([^\"]*)\"$")
    public void iAmTestingCase(String id) throws Throwable {
        MainCall.caseID = id;
    }

    @And("^I have enter To do as \"([^\"]*)\"$")
    public void iHaveEnterToDoAs(String userName) throws Throwable {
       MainCall.landingPage.enterToDo(userName);
    }
    @When("^I press the Submit button$")
    public void iPressTheSubmitButton() {
        MainCall.landingPage.clickSubmit();
    }

    @Then("^I want to see the Stitch Todo List message$")
    public void iWantToSeeStitchToDoMessage() {
        MainCall.landingPage.verifyLandingPage();
    }
    @Then("^I verify the Submit$")
    public void iWantToVerifyToDoMessage() {
        MainCall.landingPage.verifySubmit();
    }
}
