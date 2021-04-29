package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;

    private String url = "https://www.amazon.com/";
    private String searchProduct = "VTech KidiZoom Smartwatch DX2, Blue";

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchField;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage openPage(){
        driver.get(url);
        return this;
    }

    public MainPage findSearchField(){
        searchField.isDisplayed();
        return this;
    }

    public SearchPage searchForProduct(){
        searchField.sendKeys(searchProduct);
        searchField.submit();
        return new SearchPage(driver);
    }
}
