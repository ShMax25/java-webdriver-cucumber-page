package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;
import support.TestContext;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class CareersStepDefs {
    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String page) throws Throwable {
        switch (page) {
            case "careers":
                new LandingPage().open();
                break;
            case "sample":
                new SampleForm().open();
                break;
            default:
                throw new Exception("Page " + page +" not recognized!");
        }
    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Throwable {
        HashMap<String, String> user = getData(role);
        LandingPage landingPage = new LandingPage();
        landingPage.assertHeader();
        landingPage.clickLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.assertHeader();
        loginPage.login(user.get("email"), user.get("password"));
    }

    @Then("^I verify \"([^\"]*)\" login$")
    public void iVerifyLogin(String role) throws Throwable {
        HashMap<String, String> user = getData(role);
        String actualName = new LandingPage().getNameOfLoggedUser();
        assertThat(actualName).isEqualTo(user.get("name"));
    }

    @When("^I create new position$")
    public void iCreateNewPosition() throws Exception {
        new LandingPage().clickRecruit();
        RecruitPage recruitPage = new RecruitPage();
        recruitPage.assertHeader();
        recruitPage.clickNewPosition();

        NewPositionPage newPositionPage = new NewPositionPage();
        newPositionPage.assertHeader();

        HashMap<String, String> position = TestContext.getPosition();
        String title = position.get("title");
        title = TestContext.addTimestamp(title);
        TestContext.setTestData("positionTitle", title);
        position.put("title", title);
        newPositionPage.fillPosition(position);
        newPositionPage.submit();
    }

    @And("^I verify position created$")
    public void iVerifyPositionCreated() throws Exception {
        boolean isPresent = new RecruitPage().isPositionPresentOnPage(TestContext.getTestData("positionTitle"));
        assertThat(isPresent).isTrue();
    }
}
