package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Header {

    public LoginPage() {
        setHeaderText("Login");
    }

    @FindBy(xpath="//label[@for='loginUsername']/../input")
    private WebElement username;

    @FindBy(xpath="//label[@for='loginPassword']/../input")
    private WebElement password;

    @FindBy(xpath="//*[@id='loginButton']")
    private WebElement submit;

    public void login(String username, String password) {
        sendKeys(this.username, username);
        sendKeys(this.password, password);
        click(submit);
    }


}
