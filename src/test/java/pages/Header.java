package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class Header extends Page {

    private String headerText;

    @FindBy(xpath="//span[contains(@class,'position-center')]")
    private WebElement headerTitle;

    @FindBy(xpath="//a[@href='/login']")
    private WebElement login;

    @FindBy(xpath="//a[@href='/recruit']")
    private WebElement recruit;

    @FindBy(xpath="//span[@class='logout-box']/a")
    private WebElement name;

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public void clickLogin() {
        click(login);
    }

    public void clickRecruit() {
        click(recruit);
    }

    public void assertHeader() {
        assertThat(headerTitle.getText().equals(headerText)).isTrue();
    }

    public String getNameOfLoggedUser() {
        return name.getText();
    }
}
