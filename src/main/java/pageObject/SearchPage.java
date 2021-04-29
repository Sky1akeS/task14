package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class SearchPage {

    WebDriver driver;

    @FindBy(partialLinkText = "VTech KidiZoom Smartwatch DX2, Blue")
    private WebElement currentProduct;


    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public SearchPage verifyCurrentProduct(){
        Assert.assertTrue(currentProduct.isDisplayed());
        return this;
    }

    public ProductPage clickCurrentProduct(){
        currentProduct.click();
        return new ProductPage(driver);
}

}
