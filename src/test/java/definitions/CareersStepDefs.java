package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import pages.*;
import support.RestWrapper;
import support.TestContext;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

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
        boolean isPresent = new RecruitPage().isPositionPresentOnPage(TestContext.getStringTestData("positionTitle"));
        assertThat(isPresent).isTrue();
    }

    @Given("^I login to REST as \"([^\"]*)\"$")
    public void iLoginToRESTAs(String role) throws Throwable {
        HashMap<String, String> user = getData(role);
        new RestWrapper().login(user);
    }

    @When("^I create via REST new position$")
    public void iCreateViaRESTNewPosition() throws Exception {
        HashMap<String, String> position = getPositionWithTimestamp();
        new RestWrapper().createPosition(position);
    }

    @And("^I delete via REST new position$")
    public void iDeleteViaRESTNewPosition() throws Exception {
        int positionId = getJsonTestData(RestWrapper.POSITION).getInt("id");
        new RestWrapper().deletePosition(positionId);
    }

    @Then("^I verify via REST new position in the list$")
    public void iVerifyViaRESTNewPositionInTheList() throws Exception {
        JSONArray actualPositions = new RestWrapper().getPositions();
        JSONObject expectedPosition = getJsonTestData(RestWrapper.POSITION);

        boolean found = false;

        for (int i = 0; i < actualPositions.length(); i++) {
            JSONObject actualPosition = actualPositions.getJSONObject(i);
            if (actualPosition.getInt("id") == expectedPosition.getInt("id")) {
                found = true;
                break;
            }
        }
        assertThat(found).isTrue();
    }

    @And("^I update via REST new position$")
    public void iUpdateViaRESTNewPosition() throws Exception {
        HashMap<String, String> fieldsToUpdate = new HashMap<>();
        fieldsToUpdate.put("city", "Mountain View");
        int positionId = getJsonTestData(RestWrapper.POSITION).getInt("id");

        JSONObject response = new RestWrapper().updatePosition(fieldsToUpdate, positionId);
        assertThat(response.getString("city")).isEqualTo("Mountain View");

        JSONObject position = getJsonTestData(RestWrapper.POSITION);
        position.put("city", "Mountain View");
        setTestData(RestWrapper.POSITION, position);
    }

    @Then("^I verify via REST position details$")
    public void iVerifyViaRESTPositionDetails() throws Exception {
        int positionId = getJsonTestData(RestWrapper.POSITION).getInt("id");
        new RestWrapper().getPositionById(positionId);

        // verifications
    }
}
