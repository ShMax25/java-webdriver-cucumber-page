package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id='loginButton']")
    private WebElement submitButton;

    public LoginPage() {
        setUrl("http://skryabin-careers.herokuapp.com/login");
    }

    public void logIn(String username, String password) {
        waitForVisible(usernameInput);
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        click(submitButton);
    }

}
