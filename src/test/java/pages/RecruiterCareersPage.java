package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RecruiterCareersPage extends Page {

    @FindBy(xpath = "//*[@class='logout-box']/a")
    private WebElement loggedInUserName;

    @FindBy(xpath = "//*[@href='/recruit']")
    private WebElement recruitButton;

    public String getLoggedInUserName() {
        waitForClickable(recruitButton);
        return getElementText(loggedInUserName);
    }

    public void recruitButtonClick() {
        click(recruitButton);
    }


}
