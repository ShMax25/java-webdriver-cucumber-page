package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.LandingPage;
import support.TestContext;

import java.util.HashMap;

public class CareersStepDefs {
    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String page) throws Throwable {
        switch (page) {
            case "careers":
                new LandingPage().open();
                break;
            case "sample":
                System.out.println("Sample");
                break;
            default:
                throw new Exception("Page " + page +" not recognized!");
        }

    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Throwable {
        HashMap<String, String> recruiter = TestContext.getRecruiter();
//        new LandingPage().clickLogin();
//        new LoginPage().login(recruiter.get("username"), recruiter.get("password"));
    }
}
