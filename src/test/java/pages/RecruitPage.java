package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class RecruitPage extends Page {

    @FindBy(xpath = "//*[@href='/new_position']")
    private WebElement recruitButton;

    @FindBys({
            @FindBy(xpath = "//*[@class='col-sm-12 col-md-4 col-lg-3']")
    })
    private List<WebElement> allOpenPositions;

    @FindBy(xpath = "//*[@class='col-sm-12 col-md-4 col-lg-3']")
    private WebElement onePosition;

    public RecruitPage() {
        setUrl("http://skryabin-careers.herokuapp.com/recruit");
    }

    public void recruitButtonClick() {
        click(recruitButton);
    }

    public void navigateToNewlyCreatedPosition() throws InterruptedException {
        waitForNumberOfElements(allOpenPositions);
        WebElement lastPosition = allOpenPositions.get(allOpenPositions.size() - 1);
        scrollToView(lastPosition);
        click(lastPosition);
    }

}
