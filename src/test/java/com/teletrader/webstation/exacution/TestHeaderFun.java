package com.teletrader.webstation.exacution;

import com.teletrader.webstation.pages.Header_Navigation;
import com.teletrader.webstation.pages.Login;
import com.teletrader.webstation.pages.Navigation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestHeaderFun {
    WebDriver driver;
    WebDriverWait driverWait;
    Login objLogin;
    Header_Navigation objHeader;
    Navigation objNavigation;
    String baseURL = "http://webstationtest3.ttweb.net/WebStation.aspx";

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    /**
     * This test case will login to http://webstationtest3.ttweb.net/WebStation.aspx
     * Verifiy login page as Teletrader
     * Login to application
     */

    @Test(priority = 0)
    public void test_Login_page() throws InterruptedException {
        //Create login page object
        objLogin = new Login(driver);
        objHeader = new Header_Navigation(driver);
        objNavigation = new Navigation(driver);


        //test case 8: Successful login
        objLogin.succesLogin("ivan.coric91", "ictrader123");
        System.out.println(driver.findElement(By.cssSelector("a")).getText());
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo-ws")));
        Assert.assertTrue(driver.findElement(By.id("logo-ws")).isDisplayed());

        //SEARCH
        //1.0	Home button
        objHeader.homeButton();

        //2 Start typing „bank “in the „Live Search “field
        objHeader.typeBank();
        //2.1 Enter „//“in the „Live Search “field.
        objHeader.notFoundAnyResults();
        //2.3 Type “--d’ and press [Enter]
        //objHeader.changeToGerman();
        //3.2.7 Enter symbol name “BMW” in „Live Search“, and click searchButton
        objHeader.clickDetailSearch();
        //3.2.4 Enter symbol name “BMW” in „Live Search“,
        //and in expanded results click  on first symbol that appears BMW St.
        objHeader.searchSymbolBMW();
        //3.2.6 Enter symbol name “BMW” in „Live Search“, and in expanded results click on a „Table“ button.
        //objHeader.clickDetailTable();

        //HEADER CHART
        //3.3.1 Move mouse over the blue character HangS and click on dropdown
        objHeader.cickOnAsiaSymbol();
        //3.3.2 Click on DAX and compare Chn. From market overview
        //objHeader.compareHeaderChange();
        //3.2.4 Click on orange Dow and choose USD/EUR
        objHeader.clickOnEURUSD();

        //FAVORITE BUTTON
        //3.4.1 Click on  ? icon help button/ press F1 on keyboard
        //objHeader.clickOnHelp();
        //3.4.2 Click on the Open in new browser window button
        //objHeader.openInNewBrowser();
        //3.4.3 Click on the star button

        //quitbrowser
        //driver.quit();

    }
}
