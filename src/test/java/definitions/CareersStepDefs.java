package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getPosition;
import static support.TestContext.getRecruiter;


public class CareersStepDefs {

    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String page) throws Exception {
        switch (page) {
            case "careers":
                new LandingPage().open();
                break;

            default: throw new Exception("Page: " + page + " not found");
        }
    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Exception {
        HashMap<String, String> recruiter = getRecruiter();
        LandingPage landingPage = new LandingPage();
        LoginPage loginPage = new LoginPage();
        landingPage.loginClick();
        loginPage.logIn(recruiter.get("email"), recruiter.get("password"));
    }

    @Then("^I verify \"([^\"]*)\" login$")
    public void iVerifyLogin(String role) throws Exception {
        HashMap<String, String> recruiter = getRecruiter();
        RecruiterCareersPage recruiterPage = new RecruiterCareersPage();
        assertThat(recruiter.get("name")).isEqualTo(recruiterPage.getLoggedInUserName());
    }


    @When("^I create new position$")
    public void iCreateNewPosition() throws Exception {
        RecruiterCareersPage recruiterPage = new RecruiterCareersPage();
        recruiterPage.recruitButtonClick();
        RecruitPage recruitPage = new RecruitPage();
        recruitPage.recruitButtonClick();
        NewPositionPage newPositionPage = new NewPositionPage();
        HashMap<String, String> position = getPosition();
        newPositionPage.fillOpenPosition(
                                position.get("title"),
                                position.get("description"),
                                position.get("address"),
                                position.get("city"),
                                position.get("state"),
                                position.get("zip"),
                                position.get("date")
        );
    }

    @And("^I verify position created$")
    public void iVerifyPositionCreated() throws Exception {
        RecruitPage recruitPage = new RecruitPage();
        recruitPage.navigateToNewlyCreatedPosition();
        HashMap<String, String> position = getPosition();
        PositionPage positionPage = new PositionPage();
        String positionDetails = positionPage.getPositionDetailsText();
        assertThat(positionDetails).containsIgnoringCase(position.get("title"));
        assertThat(positionDetails).containsIgnoringCase(position.get("description"));
        assertThat(positionDetails).containsIgnoringCase(position.get("address"));
        assertThat(positionDetails).containsIgnoringCase(position.get("city"));
        assertThat(positionDetails).containsIgnoringCase(position.get("state"));
        assertThat(positionDetails).containsIgnoringCase(position.get("zip"));
//        assertThat(positionDetails).containsIgnoringCase(position.get("date"));
    }

}
