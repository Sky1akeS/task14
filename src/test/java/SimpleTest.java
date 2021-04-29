import factory.DriverManager;
import factory.DriverType;
import factory.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.MainPage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.apache.commons.io.FileUtils.copyFile;

public class SimpleTest {
    DriverManager driverManager;

    WebDriver driver;

    @BeforeTest
    public void setUP() {
        DriverType driverType = DriverType.fromValue(System.getenv("browser").toUpperCase());
        driverManager = WebDriverFactory.getDriver(driverType);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @Test
    public void Test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage()
                .findSearchField()
                .searchForProduct()
                .verifyCurrentProduct()
                .clickCurrentProduct()
                .findButtonToBuy()
                .clickToBuy()
                .verifyButtonAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess())
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
    }

    @AfterMethod(dependsOnMethods = "takeScreenshot")
    public void quitBrowser() {
        driverManager.quiteDriver();
    }
}
