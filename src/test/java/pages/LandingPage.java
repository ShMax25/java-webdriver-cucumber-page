package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends Page {

    @FindBy(xpath = "//*[contains(@class,'fa-sign-in')]")
    private WebElement loginButton;

    public LandingPage() {
        setUrl("http://skryabin-careers.herokuapp.com/");
    }

    public void loginClick() {
        click(loginButton);
    }

}
