package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPositionPage extends Page {

    @FindBy(xpath = "//*[@for='positionTitle']/../input")
    private WebElement titleInput;

    @FindBy(xpath = "//*[@for='positionDescription']/../textarea")
    private WebElement descriptionInput;

    @FindBy(xpath = "//*[@for='positionAddress']/../input")
    private WebElement addressInput;

    @FindBy(xpath = "//*[@for='positionCity']/../input")
    private WebElement cityInput;

    @FindBy(xpath = "//*[@for='positionZip']/../input")
    private WebElement zipInput;

    @FindBy(xpath = "//*[@for='positionState']/../select")
    private WebElement selectState;

    @FindBy(xpath = "//*[@id='positionDateOpen']")
    private WebElement dateSelect;

    @FindBy(xpath = "//*[@id='positionSubmit']")
    private WebElement submitButton;


    public NewPositionPage() {
        setUrl("http://skryabin-careers.herokuapp.com/recruit");
    }

    public void fillInTitle(String title) {
        sendKeys(titleInput, title);
    }

    public void fillInDescription(String description) {
        sendKeys(descriptionInput, description);
    }

    public void fillInAddress(String address) {
        sendKeys(addressInput, address);
    }

    public void fillInCity(String city) {
        sendKeys(cityInput, city);
    }

    public void chooseState(String state) {
        selectFromDropdownByValue(selectState, state);
    }

    public void fillInZip(String zip) {
        sendKeys(zipInput, zip);
    }

    public void selectDate(String date) {
        sendKeys(dateSelect, date);
    }

    public void clickSubmit() {
        click(submitButton);
    }


    public void fillOpenPosition(String title, String description, String address, String city, String state, String zip, String date) {
        fillInTitle(title);
        fillInDescription(description);
        fillInAddress(address);
        fillInCity(city);
        chooseState(state);
        fillInZip(zip);
        selectDate(date);
        clickSubmit();
    }

}
