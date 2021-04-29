package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {

    WebDriver driver;

    @FindBy(id = "buy-now-button")
    private WebElement buttonToBuy;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public ProductPage findButtonToBuy(){
        buttonToBuy.isDisplayed();
        return this;
    }

    public SignInPage clickToBuy(){
        buttonToBuy.click();
        return new SignInPage(driver);
    }

}
