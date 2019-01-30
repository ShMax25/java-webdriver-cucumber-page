package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionPage extends Page {

    @FindBy(xpath = "//*[@class='card-body text-left']")
    private WebElement positionDetails;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement applyButton;

    public String getPositionDetailsText() {
        waitForClickable(applyButton);
        return getElementText(positionDetails);
    }

}
